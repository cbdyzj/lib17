const compose = require('../util/compose')

/* text handlers */
const handlers = []

handlers.push((content, next) => {
    if (/^在吗？?$/.test(content)) {
        return 'nano在的'
    }
    return next()
})

handlers.push((content, next) => {
    if (content === 'hi') {
        return 'hi，你好哇！'
    }
    return next()
})

function handleTextMessage(content) {
    return compose(handlers)(content)
}

module.exports = {
    handleTextMessage,
}
