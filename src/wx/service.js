const ExpiryMap = require('expiry-map')
const {buildContext} = require('./parser/request_parser')

const {
    handleTextMessage,
} = require('./handler/text_handler')

const {
    handleImageMessage,
} = require('./handler/image_handler')

// 10 minutes
const channel = new ExpiryMap(10 * 60 * 1000)

async function wxService(request) {
    // build context
    const ctx = buildContext(request)
    ctx.channel = channel

    switch (ctx.payload.messageType) {
        // 文本
        case 'text':
            await handleTextMessage(ctx)
            break
        // 图片
        case 'image':
            await handleImageMessage(ctx)
            break
        default:
            ctx.text('怎么办呢？')
    }
    return ctx.reply()
}

module.exports = wxService
