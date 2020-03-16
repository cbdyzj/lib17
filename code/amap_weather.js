const http = require('http')
const assert = require('assert')

// 高德开放平台
const key = process.argv[2] || assert(false, 'API key required')
const wapi = adcode => `http://restapi.amap.com/v3/weather/weatherInfo?key=${key}&city=${adcode}`
const capi = ip => `http://restapi.amap.com/v3/ip?key=${key}&ip=${ip}`

function httpGetAsString(url) {
    return new Promise((resolve, reject) => {
        http.get(url, res => {
            let buffer = ''
            res.on('data', chunk => buffer += chunk)
            res.on('end', () => resolve(buffer))
            res.on('error', error => reject(error))
        })
    })
}

async function queryCity(ip) {
    const s = await httpGetAsString(capi(ip))
    const { adcode } = JSON.parse(s)
    return adcode
}

async function queryWeather(adcode) {
    const s = await httpGetAsString(wapi(adcode))
    const { province, city, weather, temperature } = JSON.parse(s).lives[0]
    return { province, city, weather, temperature }
}

http.createServer(async (req, res) => {
    if (!req.url.includes('ip')) {
        return res.end('Usage: /ip?[IP Address]')
    }
    const remoteIp = req.connection.remoteAddress
    const ip = req.url.split('?')[1] || remoteIp
    const adcode = await queryCity(ip)
    if (adcode instanceof Array && adcode.length === 0) {
        return res.end('Error IP!')
    }
    const weather = await queryWeather(adcode)
    res.setHeader('Content-type', 'application/json; charset=utf-8')
    res.end(JSON.stringify(weather))
}).listen(3000, () => console.log('Bind 3000'))
