package com.demo.mypet.repository

import com.demo.mypet.model.MyPetModel
import com.demo.mypet.model.ResultModel
import com.demo.mypet.rest.RetrofitBuilder
import com.demo.mypet.rest.util.NetworkUtil

class PetRepository {
    suspend fun getMyPet(): ResultModel<MyPetModel>? {
        return NetworkUtil.makeApiCall {
            val res = RetrofitBuilder.apiInterface.getImage()
            res
        }
    }
}