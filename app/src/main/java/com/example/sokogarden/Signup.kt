package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // find all views by use of their id
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val signupButton = findViewById<Button>(R.id.signupBtn)
        val signinTextView = findViewById<TextView>(R.id.signintxt)
        val responseTxt=findViewById<TextView>(R.id.responseTxt)

///below when a person clicks on the TextView, he/she is navigated to the singin page
        signinTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        on click of the signup button ,we want to register a person
        signupButton.setOnClickListener {
//            specify the api end point
            val api = "http://ramogi-web.alwaysdata.net/api/signup"

//            create a requestparams- It is where we are going to hold all the data
            val data = RequestParams()

//            add /append the username ,email,password and phone
            data.put("username", username.text.toString().trim())
            data.put("email", email.text.toString().trim())
            data.put("password", password.text.toString().trim())
            data.put("phone", phone.text.toString().trim())

//            import the API helper
            val helper = ApiHelper(applicationContext)

//            inside of the helper access the function post
            helper.postSignup(api, data) { message ->
                runOnUiThread {
                    responseTxt.text = message

                    // clear the details
                    email.text.clear()
                    password.text.clear()
                    phone.text.clear()
                    username.text.clear()
                    if (message.contains("success")) {
                        email.text.clear()
                        password.text.clear()
                        phone.text.clear()
                        username.text.clear()
                    }
                }
//intent to the mainActivity page
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
            }

        }
    }
}
