package com.demo.mypet.rest

import com.demo.mypet.model.MyPetModel

interface ResponseCallback {
    fun onResponse(petModel: MyPetModel?)
    fun onError(t: Throwable)
}