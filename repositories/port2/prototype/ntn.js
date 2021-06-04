const http = require('http')
const net = require('net')

const parseArgs = require('minimist')
const express = require('express')
const uuid = require('uuid/v1')
const io = require('socket.io')
const ioClient = require('socket.io-client')

const args = parseArgs(process.argv)

// ntn event enum
const NTN_EVENT = {
    ESTABLISH: 'ntn-establish', // establish tunnel
    DATA: 'ntn-data', // data event
}

function uniqueId() {
    return uuid().replace(/-/g, '')
}

function startServer(config) {
    const clientSet = {}

    function release(clientSocketId) {
        const clientContext = clientSet[clientSocketId]
        const clientName = clientContext.clientName
        const sentryServer = clientContext.sentryServer
        if (sentryServer) {
            sentryServer.close()
        }
        delete clientSet[clientSocketId]
        console.info('release', clientSocketId, clientName)
    }

    function establish(clientSocketId, options) {
        return new Promise(resolve => {
            const clientName = options.clientName
            const clientContext = clientSet[clientSocketId]
            clientContext.clientName = clientName
            clientContext.sentrySocketSet = {}

            const clientSocket = clientContext.clientSocket

            const sentryServer = net.createServer(sentrySocket => {
                const id = uniqueId() // channel ID
                clientContext.sentrySocketSet[id] = sentrySocket
                sentrySocket.on('data', (chunk) => {
                    clientSocket.emit(NTN_EVENT.DATA, { id, chunk })
                })
                sentrySocket.on('error', error => {
                    console.error(error)
                    process.exit(-1)
                })
                sentrySocket.on('close', () => {
                    delete clientContext.sentrySocketSet[id]
                })
            })
            clientContext.sentryServer = sentryServer

            clientSocket.on(NTN_EVENT.DATA, ({ id, chunk }) => {
                const sentrySocket = clientContext.sentrySocketSet[id]
                if (sentrySocket) {
                    sentrySocket.write(chunk)
                }
            })

            sentryServer.listen({
                host: options.sentryHost || 'localhost',
                port: options.sentryPort
            }, () => {
                console.info('establish', clientSocketId, clientName)
                resolve()
            })
        })
    }

    const app = express()

    app.get('/api/hi', (req, res) => {
        res.end('hi')
    })

    app.get('/api/list', (req, res) => {
        const result = Object.entries(clientSet).map(it => ({
            socketId: it[0],
            clientName: it[1].clientName
        }))
        res.json(result)
    })

    const server = http.createServer(app)

    io(server, { path: '/endpoint' })
        .on('connection', (clientSocket) => {
            const clientSocketId = clientSocket.id
            clientSet[clientSocketId] = { clientSocket }
            clientSocket.on('disconnect', message => {
                // 释放资源
                release(clientSocketId)
            })

            clientSocket.on(NTN_EVENT.ESTABLISH, async (options) => {
                await establish(clientSocketId, options)
                clientSocket.emit(NTN_EVENT.ESTABLISH)
            })
        })

    return new Promise((resolve) => {
        server.listen(config.port, () => {
            console.log('ntn started on ' + config.port)
            resolve()
        })
    })
}

function startClient(config) {
    const url = new URL(config.endpoint)
    const socket = ioClient.connect(url.origin, { path: url.pathname })

    socket.on('connect_error', (error) => console.error('connect_error: ', error.message))
    socket.on('connect_timeout', (error) => console.error('connect_timeout: ', error.message))
    socket.on('reconnect_attempt', (attempt) => console.log('reconnect_attempt:', attempt))
    socket.on('reconnect', (attempt) => console.log('reconnect:', attempt))

    const clientName = config.name || uniqueId()
    const targetHost = config['target-host'] || 'localhost'
    const targetPort = config['target-port']

    const targetSocketSet = {}

    function establish() {
        socket.on(NTN_EVENT.DATA, ({ id, chunk }) => {
            let targetSocket = targetSocketSet[id]
            if (targetSocket) {
                targetSocket.write(chunk)
            } else {
                targetSocket = net.connect({ host: targetHost, port: targetPort }, () => {
                    targetSocket.on('data', (chunk) => socket.emit(NTN_EVENT.DATA, { id, chunk }))
                    targetSocket.on('error', (error) => {
                        console.error(error)
                        process.exit(-1)
                    })
                    targetSocket.on('close', () => delete targetSocketSet[id])
                    targetSocket.write(chunk)
                })
                targetSocketSet[id] = targetSocket
            }
        })

        socket.emit(NTN_EVENT.ESTABLISH, {
            clientName,
            targetHost, targetPort,
            sentryPort: config['sentry-port'],
            sentryHost: config['sentry-host'],
        })
    }

    socket.on('connect', () => {
        console.log('connect')
        establish()
    })

    socket.on(NTN_EVENT.ESTABLISH, () => {
        console.log('server established')
    })
}

if (require.main === module) {
    switch (args.type) {
        case 'server':
            console.info('start ntn server', args)
            startServer(args)
            break
        case 'client':
            console.info('start ntn client', args)
            startClient(args)
            break
        default:
            throw new Error('Illegal argument type')
    }
}
