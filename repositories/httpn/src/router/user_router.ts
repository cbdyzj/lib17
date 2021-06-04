import * as Router from 'koa-router'
import { userService } from '../service'

export const router = new Router

// Query current user
router.get('/me', ctx => {
    const user = ctx.session.user || 'Anonymous'
    ctx.body = { user }
})

// Login user
router.post('/login', async  ctx => {
    const { username, password } = ctx.request.body as any
    const user = await userService.authenticate(username, password)
    if (user) {
        ctx.session.user = user
        return ctx.body = { message: '登录成功！', user }
    }
    return ctx.body = { message: '登录失败！' }
})

// Logout user
router.post('/logout', ctx => {
    ctx.session.user = null
    return ctx.body = { message: '登出成功！' }
})

// Register user
router.post('/register', async ctx => {
    const user: any = ctx.request.body
    try {
        await userService.register(user)
    } catch (error) {
        return ctx.body = { message: '注册失败！', error }
    }
    return ctx.body = { message: '注册成功！' }
})

// Query user list, Search by username
router.get('/list', async ctx => {
    const { username } = ctx.query
    const page = ctx.query.page | 0
    const size = ctx.query.size | 0
    return ctx.body = await userService.list({ page, size, username })

})
