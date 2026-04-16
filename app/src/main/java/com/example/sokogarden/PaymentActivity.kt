package com.example.sokogarden

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        find the views by use of their id
        val txtname =findViewById<TextView>(R.id.txtProductName)
        val txtcost =findViewById<TextView>(R.id.txtProductCost)
        val imgproduct=findViewById<ImageView>(R.id.imgProduct)
        val txtdescription=findViewById<TextView>(R.id.txtProductDescription)


//        retrive the data passed from the previous activity
        val name =intent.getStringExtra("product_name")
        val cost =intent.getIntExtra("product_cost",0)
        val product_photo=intent.getStringExtra("product_photo")
        val product_description=intent.getStringExtra("product_description")

//        Update the textview with the data passed from the previous activity
        txtname.text= name
        txtcost.text= "KES $cost"
        txtdescription.text=product_description

        // specify the image url
        val imageurl ="https://ramogi-web.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageurl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgproduct)

//        find the edit text and the button
         val phone=findViewById<EditText>(R.id.phone)
         val btnPay=findViewById<Button>(R.id.pay)

//        set click listener on the button
        btnPay.setOnClickListener {
            //specify the Api end point
            val api ="http://ramogi-web.alwaysdata.net/api/mpesa_payment"

            //create a Request Params
            val data = RequestParams()

            //Insert data into the RequestParams
            data.put("amount", cost)
            data.put("phone", phone.text.toString().trim())

            //import the helper class
            val helper= ApiHelper(applicationContext)

            //access the post function
            helper.post(api, data)

            //clear phone input
            phone.text.clear()

        }


    }
}