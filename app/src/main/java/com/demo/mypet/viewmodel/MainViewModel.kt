package com.demo.mypet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.mypet.model.MyPetModel
import com.demo.mypet.model.ResultModel
import com.demo.mypet.repository.PetRepository

class MainViewModel : ViewModel() {
    private val petRepo = PetRepository()
    private var response = MutableLiveData<MyPetModel?>()
    private var error = MutableLiveData<String?>()
    suspend fun getPet() {
        when (val myPet = petRepo.getMyPet()) {
            is ResultModel.Success -> {
                response.value = myPet.value
            }
            is ResultModel.NetworkError -> {
                error.postValue("Network error")
            }
            is ResultModel.GenericError -> {
                error.postValue(
                    (myPet.error ?: "Something went wrong. Please try again.").toString()
                )
            }
        }
    }

    fun getMyPetResponse() = response
}