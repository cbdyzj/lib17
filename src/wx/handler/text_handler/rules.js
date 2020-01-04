const {
    channels,
} = require('./channels')
const {
    getStatusText
} = require('../../../util/app_status')

const rules = [{
    pattern: /^hi$/i,
    reply: 'hi，你好哇！'
}, {
    pattern: /^在吗？?$/,
    reply: 'nano在的',
}, {
    pattern: /^你是谁？?$/,
    reply: '我是nano呀',
}, {
    pattern: /^nano?$/i,
    reply: '试试输入下列关键词让nano帮助你吧：' + '\n' + channels.map(c => c.name).join('\n')
}, {
    pattern: /^重置频道$/,
    reply(ctx) {
        ctx.channel.delete(ctx.payload.fromUserName)
        return '已重置'
    }
}, {
    pattern: /^nano状态$/i,
    reply(ctx) {
        return getStatusText()
    }
}]

const handlers = []

handlers.push((ctx, next) => {
    for (const rule of rules) {
        if (rule.pattern.test(ctx.payload.content)) {
            if (typeof rule.reply === 'string') {
                ctx.text(rule.reply)
            } else if (typeof rule.reply === 'function') {
                ctx.text(rule.reply(ctx))
            }
            return
        }
    }
    return next()
})

module.exports = handlers
