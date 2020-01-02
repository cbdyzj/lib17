const { join } = require('path')
const express = require('express')

const app = express()

// static files
app.use(express.static(join(__dirname, '..', 'static')))

// wx api
app.use(require('./wx_routes'))

// health check
app.get('/hi', (req, res) => {
    res.end('hi')
})

app.listen(3000, () => {
    console.log('Serving on port 3000')
})
