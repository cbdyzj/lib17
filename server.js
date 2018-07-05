const Koa = require('koa')
const Router = require('koa-router')
const bodyParser = require('koa-bodyparser')
const logger = require('koa-logger')

const router = new Router

router.get('/17', (ctx, nxt) => {
    ctx.body = '00010001'
})

const app = new Koa

app.use(logger())
app.use(bodyParser())
app.use(router.routes())

app.listen(3000, '127.0.0.1')
