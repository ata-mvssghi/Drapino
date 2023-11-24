package com.example.drapino.loginApi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object{
        val BASE_URL = "https://api.zarinplus.com/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
        val secondLogInApiBaseUrl = "https://betaapi.droppin.ir/"
        fun getSecondRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(secondLogInApiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }



}