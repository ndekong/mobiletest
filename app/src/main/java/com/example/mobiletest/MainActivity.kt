package com.example.mobiletest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mobiletest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

    }

    private fun getData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://assessment.api.vweb.app/")
            .build()

            .create(PlaceHolder::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<Rides>?> {
            override fun onResponse(call: Call<List<Rides>?>, response: Response<List<Rides>?>) {
                val  responseBody = response.body()!!


                val myStringBuilder = StringBuilder()
                for (Data in responseBody){
                    myStringBuilder.append(Data.id)
                    myStringBuilder.append(Data.originstation)
                    myStringBuilder.append(Data.stationpath)
                    myStringBuilder.append(Data.date)
                    myStringBuilder.append(Data.destinationstation)
                    myStringBuilder.append(Data.state)
                    myStringBuilder.append(Data.city)
                    myStringBuilder.append("\n")
                }


            }
            override fun onFailure(call: Call<List<Rides>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })

}
}