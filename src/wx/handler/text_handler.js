const {getWikiExtracts} = require('../../util/wiki_extracts')
const { channels, getChannel } = require('./channel')
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
        const message = '试试输入下列关键词让nano帮你吧：'
            + '\n' + channels.map(c => c.name).join('\n')
        ctx.text(message)
        return
    }
    return next()
})

// channel
handlers.push(async (ctx, next) => {
    const channel = getChannel(ctx.payload.content)
    if (channel) {
        // set user context
        ctx.channel.set(ctx.payload.fromUserName, channel.name)
        ctx.text(channel.prompt)
        return
    }
    return next()
})

// wiki
handlers.push(async (ctx, next) => {
    if (ctx.channel.get(ctx.payload.fromUserName) === '维基百科') {
        const wiki = await getWikiExtracts(ctx.payload.content)
        ctx.text(wiki)
        return
    }
    return next()
})

// Google
handlers.push(async (ctx, next) => {
    // if (ctx.channel.get(ctx.payload.fromUserName) === '谷歌搜索') {
    //     const result = await googleSearch(ctx.payload.content)
    //     ctx.text(result)
    //     return
    // }
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
