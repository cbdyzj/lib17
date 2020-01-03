const compose = require('../../util/compose')

/* image handlers */
const handlers = []

handlers.push(async (ctx, next) => {
    // todo
    return ctx.text('怎么办呢？')
})

function handleImageMessage(ctx) {
    return compose(handlers)(ctx)
}

module.exports = {
    handleImageMessage,
}
