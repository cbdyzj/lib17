const path = require('path')

const Koa = require('koa')
const serve = require('koa-static')
const bodyParser = require('koa-bodyparser')
const logger = require('koa-logger')
const router = require('./router')

const app = new Koa

app.use(serve(path.join(__dirname, 'static')));

app.use(logger())

app.use(bodyParser({ enableTypes: ['json', 'form', 'text'] }))

app.use(router.routes())

app.listen(3000, '127.0.0.1')
