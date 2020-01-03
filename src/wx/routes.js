const crypto = require('crypto')
const { Router } = require('express')

const { wx } = require('../config')
const wxService = require('./service')

const { token } = wx

const wxRouter = Router()

wxRouter.post('/wx',async (req,res) => {
    res.setHeader('Content-Type', 'text/xml')
    const reply = await wxService(req.body)
    res.end(reply)
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
