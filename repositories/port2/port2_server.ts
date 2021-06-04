import { serve } from "https://deno.land/std/http/server.ts"
import {
    acceptWebSocket,
    isWebSocketCloseEvent,
    isWebSocketPingEvent,
    WebSocket
} from "https://deno.land/std/ws/mod.ts"

async function handleWebSocket(sock: WebSocket) {
    const it = sock.receive();
    while (true) {
        try {
            const { done, value } = await it.next();
            if (done) {
                break;
            }
            const ev = value;
            if (typeof ev === "string") {
                // text message
                console.log("ws:Text", ev);
                await sock.send(ev);
            } else if (ev instanceof Uint8Array) {
                // binary message
                console.log("ws:Binary", ev);
            } else if (isWebSocketPingEvent(ev)) {
                const [, body] = ev;
                // ping
                console.log("ws:Ping", body);
            } else if (isWebSocketCloseEvent(ev)) {
                // close
                const { code, reason } = ev;
                console.log("ws:Close", code, reason);
            }
        } catch (e) {
            console.error(`failed to receive frame: ${e}`);
            await sock.close(1000).catch(console.error);
        }
    }
}

interface ServerOptions {
    port: number | string
}

export async function startServer(options: ServerOptions) {
    console.log(`websocket server is running on :${options.port}`)
    for await (const req of serve(`:${options.port}`)) {
        const { headers, conn, url } = req
        // websocket
        if (/^\/endpoint/i.test(url)) {
            try {
                const sock: WebSocket = await acceptWebSocket({
                    conn,
                    headers,
                    bufReader: req.r,
                    bufWriter: req.w
                })
                console.log("socket connected!")
                await handleWebSocket(sock)
            } catch (error) {
                req.respond({
                    status: 400,
                    body: `failed to accept websocket: ${error}`
                })
            }
        }
        // api
        else if (/^\/api/i.test(url)) {
            req.respond({
                body: 'API: ' + url
            })
        }
        // not found 
        else {
            req.respond({
                status: 404,
                body: '404 NOT FOUND'
            })
        }
    }
}
