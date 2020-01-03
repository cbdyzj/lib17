const { join } = require('path')
const express = require('express')
const xmlParser = require('express-xml-bodyparser')

const app = express()

// static files
app.use(express.static(join(__dirname, '..', 'static')))

// body parser
app.use(express.json())
app.use(express.urlencoded({ extended: false }))
app.use(xmlParser())

// wx api
app.use(require('./wx/routes'))

app.listen(3000, () => {
    console.log('Serving on port 3000')
})
