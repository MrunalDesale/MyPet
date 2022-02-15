package com.demo.mypet.rest.util

import com.demo.mypet.model.MyPetModel
import com.demo.mypet.rest.ResponseCallback
import com.demo.mypet.rest.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NetworkUtil {
    fun getPet(callback: ResponseCallback) {
        val call = RetrofitBuilder.apiInterface.getImage()

        call.enqueue(object : Callback<MyPetModel> {
            override fun onResponse(call: Call<MyPetModel>, response: Response<MyPetModel>) {
                callback.onResponse(response.body())
            }

            override fun onFailure(call: Call<MyPetModel>, t: Throwable) {

            }
        })
    }
}