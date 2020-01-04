const {
    channels,
} = require('./channels')
const {
    getStatusText
} = require('../../../util/app_status')

const rules = [{
    pattern: /^hi$/i,
    reply() {
        return 'hi，你好哇！'
    },
}, {
    pattern: /^在吗？?$/,
    reply() {
        return 'nano在的'
    },
}, {
    pattern: /^nano?$/i,
    reply() {
        return '试试输入下列关键词让nano帮你吧：'
            + '\n' + channels.map(c => c.name).join('\n')
    }
}, {
    pattern: /^nano状态$/i,
    reply() {
        return getStatusText()
    }
}]

const handlers = []

handlers.push((ctx, next) => {
    for (const rule of rules) {
        if (rule.pattern.test(ctx.payload.content)) {
            ctx.text(rule.reply())
            return
        }
    }
    return next()
})

module.exports = handlers
