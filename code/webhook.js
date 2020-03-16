const http = require('http')
const { spawn } = require('child_process')

function isMasterUpdate(event) {
    return event.ref === 'refs/heads/master'
        && ['push', 'merge'].includes(event.event_name)
}

function parseBody(req) {
    return new Promise((resolve, reject) => {
        let data = ''
        req.on('data', chunk => data += chunk)
        req.on('end', () => resolve(JSON.parse(data)))
        req.on('error', error => reject(error))
    })
}

function runTask(...args) {
    const cp = spawn(...args)
    cp.stdout.on('data', (data) => console.log(`STDOUT:\n${data}`))
    cp.stderr.on('data', (data) => console.log(`STDERR:\n${data}`))
    cp.on('close', (code) => console.log(`EXIT CODE: ${code}`))
}

async function app(req, res) {
    const event = await parseBody(req)
    if (isMasterUpdate(event)) {
        runTask('sh', ['./hook.sh'])
    }
    res.end()
}

http.createServer(app).listen(3003, () => console.log('listen 3003'))
