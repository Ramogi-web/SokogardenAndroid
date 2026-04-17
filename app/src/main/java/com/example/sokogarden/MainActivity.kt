package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find the buttons by use of their id
        val SigninButton = findViewById<Button>(R.id.signinbtn)
        val SignupButton = findViewById<Button>(R.id.signupbtn)

//        create the intent for the two activities
        SignupButton.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
//        ====================
        SigninButton.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        find the recycler view and the progress bar by use of their id
        val recyclerView =findViewById<RecyclerView>(R.id.recyclerview)
        val progressBar =findViewById<ProgressBar>(R.id.progressbar)

//        specify the API URL endpoint for fetching the products
        val url ="http://ramogi-web.alwaysdata.net/api/get_products"

//        Import the helper class
        val helper = ApiHelper(applicationContext)

//        Inside of the helper class access the function Load products
        helper.loadProducts(url,recyclerView,progressBar)

//        find the about button by use of their id and have the intent
        val aboutButton=findViewById<Button>(R.id.aboutbtn)

//        the intent to the about activity
        aboutButton.setOnClickListener {
            val intent= Intent(applicationContext, About::class.java)
            startActivity(intent)
        }

    }
}