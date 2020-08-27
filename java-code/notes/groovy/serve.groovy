#!/usr/bin/env groovy
@Grab('io.vertx:vertx-web:3.9.2')
import io.vertx.core.Vertx

def port = 8000
Vertx.vertx().createHttpServer().requestHandler({ req ->
    req.response()
            .putHeader("content-type", "text/plain")
            .end("hello world")
}).listen(port)

print("Vert.x server running on $port")
