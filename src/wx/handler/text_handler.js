const compose = require('../../util/compose')

/* text handlers */
const handlers = []

handlers.push((ctx, next) => {
    if (/^在吗？?$/.test(ctx.payload.content)) {
        ctx.text('nano在的')
        return
    }
    return next()
})

handlers.push((ctx, next) => {
    if (ctx.payload.content === 'hi') {
        return ctx.text('hi，你好哇！')
    }
    return next()
})

handlers.push((ctx, next) => {
    if (ctx.payload.content === '植物识别') {
        ctx.text('10分钟内上传图片会识别植物哦')
        return
    }
    return next()
})

// echo eventually
handlers.push((ctx, next) => {
    ctx.text(ctx.payload.content)
})

function handleTextMessage(ctx) {
    return compose(handlers)(ctx)
}

module.exports = {
    handleTextMessage,
}
