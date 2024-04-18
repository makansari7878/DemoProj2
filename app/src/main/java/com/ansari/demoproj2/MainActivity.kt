package com.ansari.demoproj2

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softaai.beauty_android.data.ApiCllient
import com.softaai.beauty_android.data.HeadersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var myUserList: ArrayList<HeadersData>
    lateinit var myAdapter: MyAdapter // Changed to MyUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
       // toolbar.logo = toolbar.context.getDrawable(R.drawable.koc1) // Replace with your logo drawable

        supportActionBar?.title = "Adaptive Cruise Control"
        toolbar.setBackgroundColor(Color.BLUE)
        toolbar.setTitleTextColor(Color.WHITE)


        myUserList = ArrayList()
        myAdapter = MyAdapter(myUserList)

        val myRecyclerView = findViewById<RecyclerView>(R.id.myrecyclerview)
        myRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myRecyclerView.adapter = myAdapter
    }

    private fun fetchData() {
        val makeCall = ApiCllient.retrofitBuilder.getData()

        makeCall.enqueue(object : Callback<List<HeadersData>> {
            override fun onResponse(call: Call<List<HeadersData>>?, response: Response<List<HeadersData>>?) {
                val usersList: List<HeadersData>? = response?.body()
                if (usersList != null) {
                    myUserList.clear() // Clear existing data
                    myUserList.addAll(usersList) // Add new data
                    myAdapter.notifyDataSetChanged() // Notify adapter about the changes
                }
            }

            override fun onFailure(call: Call<List<HeadersData>>?, t: Throwable?) {
                Log.i("mytag", "Error is ${t.toString()}")
            }
        })
    }
}