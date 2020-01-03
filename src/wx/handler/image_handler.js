const compose = require('../../util/compose')
const url2base64 = require('../../util/url2base64')
const client = require('../../baidu/plant_client')

/* image handlers */
const handlers = []

// plant
handlers.push(async (ctx, next) => {
    try {
        const image = await url2base64(ctx.payload.pictureUrl)
        const result = await client(image)
        const baikeInfo = result.result[0].baike_info
        const info = baikeInfo.description + '\n' + baikeInfo.baike_url
        return ctx.text(info)
    } catch (error) {
        return ctx.text('nano遇到了一些问题：' + error.message)
    }

})

// eventually
handlers.push(async (ctx, next) => {
    return ctx.text('怎么办呢？')
})

function handleImageMessage(ctx) {
    return compose(handlers)(ctx)
}

module.exports = {
    handleImageMessage,
}
