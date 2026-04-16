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

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        find the two edit texts/a button and a textview by use of their ids

        val email = findViewById<EditText>(R.id.email)
        val password =findViewById<EditText>(R.id.password)
        val signinButton = findViewById<Button>(R.id.signinBtn)
        val signupTextView = findViewById<TextView>(R.id.signuptxt)

//        On the textview set onclicklistener that when clicked it navigates you to the the signup page
        signupTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }

//        on click of the button signin ,we need to interact with our API endpoint as we pass the two data info email and password
        signinButton.setOnClickListener {

            // specify the API endpoint
            val api ="http://ramogi-web.alwaysdata.net/api/signin"

            // create a RequestParams that will enable you to hold the data in form of a bundle/package
            val data = RequestParams()

            //append/add/attach the email and the password
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

            //import the API helper
            val helper = ApiHelper(applicationContext)

            //By use of the function post_login inside the helper class post your data
            helper.post_login(api, data)

        }

    }
}