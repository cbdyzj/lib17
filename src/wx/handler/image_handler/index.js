const compose = require('../../../util/compose')
const url2base64 = require('../../../util/url2base64')
const client = require('../../../baidu/plant_client')

/* image handlers */
const handlers = []

handlers.push(...require('./plant'))

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
