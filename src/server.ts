import path from 'path'

import Koa from 'koa'
import serve from 'koa-static'
import bodyParser from 'koa-bodyparser'
import logger from 'koa-logger'
import router from './router'

const app = new Koa

app.use(serve(path.join(__dirname, '..', 'dist')));

app.use(logger())

app.use(bodyParser({ enableTypes: ['json', 'form', 'text'] }))

app.use(router.routes())

app.listen(3000, '127.0.0.1')
