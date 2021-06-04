import * as http from 'http'
import app from './app'
import { log } from './util'
import { configuration } from './configuration'

const { port } = configuration

http.createServer(app.callback()).listen(port, () => log.info(`bind ${port}`))
