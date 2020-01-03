const ExpiryMap = require('expiry-map')
const { buildContext } = require('./parser/request_parser')

const {
    handleTextMessage,
} = require('./handler/text_handler')

const {
    handleImageMessage,
} = require('./handler/image_handler')

const plantMap = new ExpiryMap

function wxService(request) {
    // build context
    const ctx = buildContext(request)
    ctx.plantMap = plantMap

    switch (ctx.payload.messageType) {
        // 文本
        case 'text':
            return handleTextMessage(ctx)
        // 图片
        case 'image':
            return handleImageMessage(ctx)
        default:
            return ctx.text('怎么办呢？')
    }
}

module.exports = wxService
