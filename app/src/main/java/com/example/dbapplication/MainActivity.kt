package com.example.dbapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dbapplication.dao.User
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
        /*CoroutineScope(Dispatchers.IO).launch {
            embeddedServer(Netty, 8080,"192.168.1.85") {
                routing {
                    get("/hello") {
                        call.respondText("Hello, API!")
                    }
                }
            }.start(wait = true)
        }*/

    }

    override fun onResume() {
        super.onResume()
        val tvText = findViewById<TextView>(R.id.tvTest)
        lifecycleScope.launch {
            Application.DAO.getAll().collect{ userList ->
                tvText.text = ""
                for (item in userList){
                    item.firstName.toString()
                    tvText.append(item.firstName)
                    tvText.append(" ")
                    tvText.append(item.lastName)
                    tvText.append(" ")
                    tvText.append(item.age.toString())
                    tvText.append("\n")
                }
            }
        }
    }

    private fun setListener() {
        val insertButton = findViewById<Button>(R.id.button)
        val deleteButton = findViewById<Button>(R.id.button2)
        val retrofitButton = findViewById<Button>(R.id.button3)
        insertButton.setOnClickListener(this)
        deleteButton.setOnClickListener(this)
        retrofitButton.setOnClickListener(this)

    }

    @SuppressLint("CutPasteId")
    override fun onClick(v: View?) {
        val id =v!!.id
        if (id == R.id.button){
            val users = User(
                findViewById<TextView>(R.id.firstNameEditText).text.toString(),
                findViewById<TextView>(R.id.lastNameTextEdit).text.toString(),
                findViewById<TextView>(R.id.ageTextPlainEdit).text.toString().toInt())
            val tvText = findViewById<TextView>(R.id.tvTest)
            tvText.text = ""
            lifecycleScope.launch {
                Application.DAO.insertAll(users)
            }
        } else if (id == R.id.button2) {
            lifecycleScope.launch {
                try {
                    Application.DAO.findByName(findViewById<TextView>(R.id.firstNameEditText).text.toString(),
                        findViewById<TextView>(R.id.lastNameTextEdit).text.toString()).collect{
                        Application.DAO.delete(it)
                    }
                } catch (e:Exception) {
                    println("таких данных нет")
                }
            }
        } else {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            this@MainActivity.startActivity(intent)
        }
    }
}
