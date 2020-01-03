module.exports = function compose(middlewares) {
    if (!middlewares || !middlewares.length) {
        return ctx => ctx
    }
    return ctx => middlewares[0](ctx, () => compose(middlewares.slice(1))(ctx))
}
