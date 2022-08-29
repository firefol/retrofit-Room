package com.example.dbapplication

import android.app.Application
import androidx.room.Room
import com.example.dbapplication.dao.AppDatabase
import com.example.dbapplication.dao.User
import com.example.dbapplication.dao.UserDao
import com.example.dbapplication.server.Server
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class Application: Application() {


    companion object {
        lateinit var DAO: UserDao
        lateinit var retrofit: Retrofit

    }

    override fun onCreate() {
        super.onCreate()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
        DAO = db.userDao()
        retrofit = Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com")
            .build()
        /*CoroutineScope(Dispatchers.IO).launch {
            embeddedServer(Netty, 8080, "192.168.1.85") {
                routing {
                    get("/v1/users") {
                        val users = DAO.getAll()
                        val wsx = qwe(users)
                        if (users != null) {
                            call.respond(HttpStatusCode.OK, wsx)
                        } else {
                            call.respond(HttpStatusCode.NotFound)
                        }
                    }
                }
            }.start(wait = true)
        }*/
    }

/*    suspend fun qwe(users: Flow<List<User>>): String {
        val qaz = users.collect { userList ->
            var qaz = ""
            for (item in userList) {
                qaz = (qaz + item.firstName + item.lastName + "\n")
            }
        }.toString()
        return qaz
    }*/


    override fun onTerminate() {
        super.onTerminate()
    }

}