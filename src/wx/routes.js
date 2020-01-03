const crypto = require('crypto')
const { Router } = require('express')

const { token } = require('../config')
const wxService = require('./service')

const wxRouter = Router()

wxRouter.post('/wx',(req,res) => {
    const payload = req.body
    const result = wxService(payload)
    res.setHeader('Content-Type', 'text/xml')
    res.end(result)
})

wxRouter.get('/wx', (req, res) => {
    const { signature, echostr, timestamp, nonce } = req.query

    const joined = [token, timestamp, nonce].sort().join('')
    const digest = crypto.createHash('sha1')
        .update(joined)
        .digest('hex')

    if(digest === signature){
        res.end(echostr)
        return
    }
    res.end('')
})

module.exports = wxRouter
