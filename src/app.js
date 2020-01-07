const { join } = require('path')
const express = require('express')

const app = express()

// static files
app.use(express.static(join(__dirname, '..', 'static')))

// body parser
app.use(express.json())
app.use(express.urlencoded({ extended: false }))

// wx api
app.use(require('./routes'))

app.listen(3000, () => {
    console.log('Serving on port 3000')
})
