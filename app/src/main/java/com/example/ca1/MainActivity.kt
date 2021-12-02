package com.example.ca1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ca1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://inshortsapi.vercel.app/news?category=")
                .addConverterFactory(GsonConverterFactory.create()) //json converter cambio de json a data "normal"
                .build()
    }

    private fun searchByCategory(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getNewsByData("$query")
            // only response, not the onject that I want
            val news = call.body() //this is really where the response is
            if (call.isSuccessful){
                //show
            }else{
                //error
            }
        }
    }
}