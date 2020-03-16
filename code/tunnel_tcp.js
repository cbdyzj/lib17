#!/usr/bin/env node
const net = require('net')
const assert = require('assert')

function tunnelTcp(host, port) {
    const server = net.createServer(source => {
        const destination = net.connect(
            { host, port },
            () => source.on('data', data => destination.write(data)),
        )
        destination.on('data', data => source.write(data))

        destination.on('error', error => source.destroy())
        source.on('error', error => destination.destroy())
    })
    return { from: server.listen.bind(server) }
}

if (require.main === module) {
    const sourcePort = process.argv[2]
    const targetPort = process.argv[3]
    assert(sourcePort, 'Source port required')
    assert(targetPort, 'Target port required')
    const targetHost = process.argv[4] || 'localhost'
    console.log(`Forwarding from :${sourcePort} to ${targetHost}:${targetPort}`)
    tunntlTcp(targetHost, targetPort).from(sourcePort)
}
