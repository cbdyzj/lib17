import { startServer } from './port2_server.ts'
import {startClient} from './port2_client.ts'

const args = Deno.args

switch (args[0]) {
    case 'server':
        /* port2 server */
        const port = '8080'
        await startServer({ port })
        break
    case 'client':
        /* port2 server */
        await startClient({})
        break
    default:
        console.log('Illegal arguments: ', args)
}
