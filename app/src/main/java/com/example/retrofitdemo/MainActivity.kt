package com.example.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET





class MainActivity : AppCompatActivity() {
    var mList:ArrayList<Users> = ArrayList()
     var adapterUsers:AdapterUsers? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        getData()
        adapterUsers = AdapterUsers(this, mList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapterUsers

    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://grocery-second-app.herokuapp.com/")
//                .addConverterFactory(ScalarsConverterFactory.create()) /// for getting data as string
            .addConverterFactory(GsonConverterFactory.create()) /// for getting data as class
            .build()

        val service:GitHubService = retrofit.create(GitHubService::class.java)
        val call = service.getData()
        Log.d("abc", call.toString())

        ///Get actual data
        //Asynchronus request needs a new thread
//        Thread{
//            val response = call.execute()
//            val data = response.body()
//            Log.d("abc1", data.toString())
//
//        }.start()


        //Synchronous request
//        // New thread is not required
////        Thread{
        val response = call.enqueue(object: Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                val usersResponse = response.body()
                Log.d("abc2", usersResponse.toString())
                Log.d("data", "first user" + usersResponse!!.data[0].firstName)
                mList.addAll(usersResponse.data)
                adapterUsers!!.setData(mList)

            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.d("abc3", t.message.toString())
            }

        })

////
////        }.start()
    }


}

interface GitHubService {
    @GET("/api/users")
    fun getData(): Call<UsersResponse>
}


