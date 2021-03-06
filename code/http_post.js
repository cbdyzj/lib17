var http = require('http')

const cr = http.request({
    host: 'localhost',
    port: '80',
    path: '/endpoint',
    method: 'post'
}, res => {
    var buffer = ''
    res.on('data', chunk => buffer += chunk)
    res.on('error', error => console.error(error))
    res.on('end', () => console.log(buffer))
})

cr.write('')
cr.end()