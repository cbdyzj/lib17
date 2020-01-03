const compose = require('../../util/compose')

/* text handlers */
const handlers = []

// hi
handlers.push((ctx, next) => {
    if (ctx.payload.content === 'hi') {
        ctx.text('hi，你好哇！')
        return
    }
    if (/^在吗？?$/.test(ctx.payload.content)) {
        ctx.text('nano在的')
        return
    }
    return next()
})

// nano
handlers.push((ctx, next) => {
    if (/^nano?$/i.test(ctx.payload.content)) {
        ctx.text('试试看输入：“植物识别”吧')
        return
    }
    return next()
})

// plant
handlers.push((ctx, next) => {
    if (ctx.payload.content === '植物识别') {
        // set user context
        ctx.channel.set(ctx.payload.fromUserName, '植物识别')
        ctx.text('10分钟内上传图片能识别植物哦（为了防止图片太大，请避免上传原图哦）')
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
