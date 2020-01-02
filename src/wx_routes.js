const crypto = require('crypto')
const { Router } = require('express')

const { token } = require('./config')

const wxRouter = Router()

const sha1Hash = crypto.createHash('sha1')

wxRouter.get("/wx", (req, res) => {
    const { signature, echostr, timestamp, nonce } = req.query

    const joined = [token, timestamp, nonce].sort().join('')
    const digest = sha1Hash.update(joined).digest('hex')

    if(digest === signature){
        res.end(echostr)
        return
    }
    res.end()
})

module.exports = wxRouter
