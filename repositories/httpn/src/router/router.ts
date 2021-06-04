import * as Router from 'koa-router'
import { router as userRouter } from './user_router'

export const router = new Router

router.get('/api/ping', ctx => ctx.body = 'pong')

router.use('/api/user', userRouter.routes())
