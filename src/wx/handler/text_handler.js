const { getWikiExtracts } = require('../../util/wiki_extracts')
const { channels } = require('./channel')
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
            + '\n' + channels.join('\n')
        ctx.text(message)
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
    if (ctx.payload.content === '维基百科') {
        // set user context
        ctx.channel.set(ctx.payload.fromUserName, '维基百科')
        ctx.text('10分钟内发送关键词将从维基百科查询词条')
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
    if (ctx.payload.content === '谷歌搜索') {
        // set user context
        ctx.channel.set(ctx.payload.fromUserName, '谷歌搜索')
        ctx.text('谷歌搜索功能还在施工中🔧，请稍后试试')
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
