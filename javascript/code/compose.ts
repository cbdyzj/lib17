export type Middleware = (ctx: any, nxt: () => any) => any

export function compose(middlewares: Middleware[]) {
    if (!middlewares || !middlewares.length) {
        return ctx => ctx
    }
    return ctx => middlewares[0](ctx, () => compose(middlewares.slice(1))(ctx))
}
