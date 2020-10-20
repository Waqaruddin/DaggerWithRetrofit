package com.example.retrofitdemo

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MyModule {
    @Provides
    fun getData(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://grocery-second-app.herokuapp.com/")
//                .addConverterFactory(ScalarsConverterFactory.create()) /// for getting data as string
            .addConverterFactory(GsonConverterFactory.create()) /// for getting data as class
            .build()
    }


    @Provides
    fun getGitHubService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)

    }
}