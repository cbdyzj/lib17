const os = require('os')

function formatMB(bytes) {
    return (bytes / 1024 / 1024).toFixed(2) + 'MB';
}

function getAppStatusText() {
    const result = []
    result.push('version: ' + process.version)
    result.push('process uptime: ' + Math.trunc(process.uptime()) + ' seconds')
    const memoryUsage = process.memoryUsage()
    result.push('rss: ' + formatMB(memoryUsage.rss))
    result.push('heapTotal: ' + formatMB(memoryUsage.heapTotal))
    result.push('heapUsed: ' + formatMB(memoryUsage.heapUsed))
    result.push('external: ' + formatMB(memoryUsage.external))
    result.push('type: ' + os.type())
    result.push('release: ' + os.release())
    result.push('platform: ' + os.platform())
    result.push('arch: ' + os.arch())
    result.push('os uptime: ' + os.uptime() + ' seconds')
    const cpus = os.cpus()
    const count = cpus.length > 1 ? ' * ' + cpus.length : ''
    result.push('cpus: ' + cpus[0].model + count)
    result.push('totalmem: ' + formatMB(os.totalmem()))
    result.push('freemem: ' + formatMB(os.freemem()))

    return result.join('\n')
}

module.exports = {
    getStatusText: getAppStatusText,
}
