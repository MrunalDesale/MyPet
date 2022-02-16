package com.demo.mypet.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.demo.mypet.R
import com.demo.mypet.repository.PetRepository
import com.demo.mypet.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModelFactory: MainViewModel.MainViewModelFactory
    private val mainViewModel: MainViewModel by lazy {
        run {
            ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        }
    }

    private lateinit var ivPet: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initView() {
        ivPet = findViewById(R.id.iv_pet)
    }

    private fun initViewModel() {
        val repository = PetRepository()
        mainViewModelFactory = MainViewModel.MainViewModelFactory(repository)
        mainViewModel.getMyPetResponse().observe(this, {
            Glide.with(this).load(it?.message).into(ivPet)
        })
        mainViewModel.getPet()
    }
}