import { join } from 'path'
import Koa from 'koa'
import serve from 'koa-static'
import bodyParser from 'koa-bodyparser'
import session from 'koa-session'

import { router } from './router'

const app = new Koa
app.keys = ['koa']

app.use(serve(join(__dirname, '..', 'static')))

app.use(bodyParser())
app.use(session(app))

app.use(router.routes())

export default app
