const compose = require('../../util/compose')
const url2base64 = require('../../util/url2base64')
const client = require('../../baidu/plant_client')

/* image handlers */
const handlers = []

// plant
handlers.push(async (ctx, next) => {
    // if(false){
    //    return next()
    // }
    try {
        console.log(ctx)
        const image = await url2base64(ctx.payload.pictureUrl)
        const result = await client(image)
        console.log(result)
        const primary = result.result[0]
        if (primary.name === '非植物') {
            ctx.text('nano觉得这个不是植物哦')
            return
        }
        const wikiInfo = primary.baike_info
        const info = '【' + primary.name + '】\n'
            + wikiInfo.description + '\n'
            + wikiInfo.baike_url

        ctx.text(info)
    } catch (error) {
        console.error(error)
        ctx.text('nano遇到了一些问题：' + error.message)
    }

})

// eventually
handlers.push(async (ctx, next) => {
    ctx.text('怎么办呢？')
})

function handleImageMessage(ctx) {
    return compose(handlers)(ctx)
}

module.exports = {
    handleImageMessage,
}
