const { join } = require('path')
const express = require('express')

const app = express()

app.use(express.static(join(__dirname, '..', 'static')))

app.get('/api/hello', (req, res) => {
    res.end('Hello World!')
})

app.listen(3000, () => {
    console.log('Serving on port 3000')
})
