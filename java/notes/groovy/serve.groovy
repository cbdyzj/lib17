#!/usr/bin/env groovy
@Grab('io.vertx:vertx-web:3.8.3')
import io.vertx.core.Vertx

def port = 8000

def serve = { req ->
    req.response()
            .putHeader("content-type", "text/plain")
            .end("hello world")
}

Vertx.vertx().createHttpServer().requestHandler(serve).listen(port)

print("Vert.x server running on $port\n")
