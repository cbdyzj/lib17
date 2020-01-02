const crypto = require('crypto')
const { Router } = require('express')

const { token } = require('./config')

const wxRouter = Router()

wxRouter.get("/wx", (req, res) => {
    const { signature, echostr, timestamp, nonce } = req.query

    const joined = [token, timestamp, nonce].sort().join('')
    const digest = crypto.createHash('sha1')
        .update(joined)
        .digest('hex')

    if(digest === signature){
        res.end(echostr)
        return
    }
    res.end()
})

module.exports = wxRouter
