package com.example.dbapplication.server

import com.example.dbapplication.Application
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

class Server {
    fun startServer() {
        embeddedServer(Netty, 8080,"192.168.1.85") {
            routing {
                get("/v1/users") {
                    val users = Application.DAO.getAll()
                    if (users != null) {
                        call.respond(HttpStatusCode.OK, users.toString())
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                }
            }
        }.start(wait = true)
    }
}