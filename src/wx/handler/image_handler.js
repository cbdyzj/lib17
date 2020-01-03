const compose = require('../../util/compose')
const url2base64 = require('../../util/url2base64')
const client = require('../../baidu/plant_client')

/* image handlers */
const handlers = []

// plant
handlers.push(async (ctx, next) => {
    if (ctx.channel.get(ctx.payload.fromUserName) !== '植物识别') {
        return next()
    }
    try {
        const image = await url2base64(ctx.payload.pictureUrl)
        const result = await client(image)
        console.log(result)
        const primary = result.result[0]
        if (primary.name === '非植物') {
            ctx.text('nano觉得这个不是植物哦')
            return
        }
        const wikiInfo = primary.baike_info
        let info = primary.name
        if (wikiInfo.description) {
            info += '\n' + wikiInfo.description
        }
        if (wikiInfo.baike_url) {
            info += '\n' + wikiInfo.baike_url
        }
        ctx.text(info)
    } catch (error) {
        console.error(error)
        ctx.text('nano遇到了一些问题：' + error.message)
    }
})

// eventually
handlers.push(async (ctx, next) => {
    ctx.text('很显然，这是一张图片')
})

function handleImageMessage(ctx) {
    return compose(handlers)(ctx)
}

module.exports = {
    handleImageMessage,
}
