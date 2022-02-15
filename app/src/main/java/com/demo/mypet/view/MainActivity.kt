package com.demo.mypet.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.demo.mypet.R
import com.demo.mypet.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
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
        mainViewModel.getMyPetResponse().observe(this, {
            Glide.with(this).load(it?.message).into(ivPet)
        })

        mainViewModel.getPet()
    }
}