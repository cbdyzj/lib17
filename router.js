const Router = require('koa-router')

const util = require('util')

const marked = require('marked')

const router = new Router

router.get('/17', ctx => {
    ctx.body = { hello: 0b00010001 }
})

const markdown = util.promisify(marked)

router.post('/markdown', async ctx => {
    const { body } = ctx.request
    try {
        ctx.body = await markdown(body)
    } catch (error) {
        ctx.body = error
    }
})

router.all('/notify', ctx => {
    const { query, body } = ctx.request
    console.log(query)
    console.log(body)
    ctx.body = 'success'
})

module.exports = router
