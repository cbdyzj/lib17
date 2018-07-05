const Router = require('koa-router')

const router = new Router

router.get('/', ctx => {
    ctx.body = { hello: 0b00010001 }
})

router.get('/17', ctx => {
    ctx.body = '00010001'
})

router.all('/notify', ctx => {
    const { query, body } = ctx.request
    console.log(query)
    console.log(body)
    ctx.body = 'success'
})

module.exports = router