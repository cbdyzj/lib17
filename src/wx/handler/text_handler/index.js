const { getWikiExtracts } = require('../../../util/wiki_extracts')
const compose = require('../../../util/compose')

const handlers  = []

handlers.push(...require('./rules'))
handlers.push(...require('./channels'))

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
    if (ctx.channel.get(ctx.payload.fromUserName) === '谷歌搜索') {
        const keywords = ctx.payload.content
        let result = 'nano生成了搜索链接：' + keywords
        result += '\n' + 'https://www.google.com/search?q=' + encodeURIComponent(keywords)
        ctx.text(result)
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
