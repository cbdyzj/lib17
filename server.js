const Koa = require('koa')
const Router = require('koa-router')

const router = new Router

router.get('/17', (ctx, nxt) => {
    ctx.body = '00010001'
})

const app = new Koa

app.use(router.routes())

app.listen(3000, '127.0.0.1')
