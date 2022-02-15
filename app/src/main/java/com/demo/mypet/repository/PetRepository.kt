package com.demo.mypet.repository

import androidx.lifecycle.MutableLiveData
import com.demo.mypet.model.MyPetModel
import com.demo.mypet.rest.ResponseCallback
import com.demo.mypet.rest.util.NetworkUtil

object PetRepository {
    var myPetLiveData = MutableLiveData<MyPetModel?>()
    fun getMyPet() {
        NetworkUtil.getPet(object : ResponseCallback {
            override fun onResponse(petModel: MyPetModel?) {
                myPetLiveData.value = petModel
            }

            override fun onError(t: Throwable) {

            }
        })
    }
}