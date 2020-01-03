const compose = require('../../util/compose')

/* text handlers */
const handlers = []

handlers.push((ctx, next) => {
    if (/^在吗？?$/.test(ctx.payload.content)) {
        return ctx.text('nano在的')
    }
    return next()
})

handlers.push((ctx, next) => {
    if (ctx.payload.content === 'hi') {
        return ctx.text('hi，你好哇！')
    }
    return next()
})

// echo eventually
handlers.push((ctx, next) => {
    const text =  ctx.payload.content
    return ctx.text(text)
})

function handleTextMessage(ctx) {
    return compose(handlers)(ctx)
}

module.exports = {
    handleTextMessage,
}
