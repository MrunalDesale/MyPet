package com.demo.mypet.rest

import com.demo.mypet.model.MyPetModel
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("random")
    fun getImage(): Call<MyPetModel>
}