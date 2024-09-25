package com.example.volleyapi

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.volleyapi.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val url = "https://meme-api.com/gimme"
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        getMemeData()
        binding.loadMeme.setOnClickListener{
            getMemeData()
        }
    }

        private fun getMemeData() {

            val ProgressDialog = ProgressDialog(this)
            ProgressDialog.setMessage("Please Wait a Moment")
            ProgressDialog.show()

        val queue = Volley.newRequestQueue(this)


        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                var responseObject = JSONObject(response)
                binding.memeTitle.text = responseObject.getString("title")
                binding.memeAuthor.text = responseObject.getString("author")
                Glide.with(this@MainActivity).load(responseObject.get("url")).into(binding.memeImage)
                ProgressDialog.dismiss()

                Log.e("Response", "getMemeData: " + response.toString())
            },
            { error->
                Toast.makeText(this@MainActivity,error.localizedMessage, Toast.LENGTH_SHORT).show()
                ProgressDialog.dismiss()

            })


        queue.add(stringRequest)

    }



    }
