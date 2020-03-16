#!/usr/bin/env node

const HttpProxy = require('http-proxy')
const http = require('http')
const assert = require('assert')

const proxy = HttpProxy.createProxyServer({ changeOrigin: true })

function reverseProxy({ staticServer, apiServer }) {
    function route(req, res) {
        if (/(\.(html|js|txt|png|jpg|css|ico)$)|(^\/?$)/.test(req.url) && req.method === 'GET') {
            proxy.web(req, res, { target: staticServer })
            return
        }
        proxy.web(req, res, { target: apiServer })
    }
    const message = `Proxying on 5000\nStatic server ${staticServer}\nAPI server ${apiServer}`
    http.createServer(route).listen(5000, () => console.log(message))
}

if (require.main === module) {
    const staticServer = process.argv[2]
    const apiServer = process.argv[3]
    assert(staticServer, 'Static server required')
    assert(apiServer, 'API server required')
    reverseProxy({ staticServer, apiServer })
}
