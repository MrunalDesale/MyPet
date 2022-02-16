package com.demo.mypet.viewmodel

import androidx.lifecycle.*
import com.demo.mypet.model.MyPetModel
import com.demo.mypet.model.ResultModel
import com.demo.mypet.repository.PetRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PetRepository) : ViewModel() {
    private var response = MutableLiveData<MyPetModel?>()

    private var error = MutableLiveData<String?>()
    fun getPet() {
        viewModelScope.launch {
            when (val myPet = repository.getMyPet()) {
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
    }

    class MainViewModelFactory(var repository: PetRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown class")
        }
    }

    fun getMyPetResponse(): LiveData<MyPetModel?> {
        return response
    }
}