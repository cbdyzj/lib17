export function compose(middlewares = (ctx, next) => (void 0)) {
    if (!middlewares || !middlewares.length) {
        return ctx => (void 0)
    }
    return ctx => middlewares[0](ctx, () => compose(middlewares.slice(1))(ctx))
}
