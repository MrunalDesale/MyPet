package com.demo.mypet.rest

import com.demo.mypet.model.MyPetModel
import retrofit2.http.GET

interface RestApi {

    @GET("random")
    suspend fun getImage(): MyPetModel
}