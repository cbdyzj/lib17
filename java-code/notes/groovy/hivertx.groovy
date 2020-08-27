#!/usr/bin/env groovy
@Grab('io.vertx:vertx-web:3.8.3')
import io.vertx.core.Vertx

Vertx.vertx().createHttpServer().requestHandler({ req ->
    req.response()
            .putHeader("content-type", "text/plain")
            .end("hello world")
}).listen(8080)

