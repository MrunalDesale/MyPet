package com.demo.mypet.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.mypet.repository.PetRepository

class MainViewModel : ViewModel() {

    private var myPet = PetRepository.myPetLiveData
    fun getPet() {
        PetRepository.getMyPet()
    }

    fun getMyPetResponse() = myPet
}