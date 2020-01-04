const url2base64 = require('../../../util/url2base64')
const client = require('../../../baidu/plant_client')

const handlers = []

// plant
handlers.push(async (ctx, next) => {
    if (ctx.channel.get(ctx.payload.fromUserName) !== '植物识别') {
        return next()
    }
    try {
        const image = await url2base64(ctx.payload.pictureUrl)
        const result = await client(image)
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

module.exports = handlers
