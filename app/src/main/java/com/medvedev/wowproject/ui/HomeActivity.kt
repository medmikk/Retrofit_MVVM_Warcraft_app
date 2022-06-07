package com.medvedev.wowproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.medvedev.wowproject.R
import com.medvedev.wowproject.api.RetrofitInstance
import com.medvedev.wowproject.repository.HomeRepository
import com.medvedev.wowproject.ui.viewmodel.HomeViewModel
import com.medvedev.wowproject.ui.viewmodel.HomeViewModelProviderFactory

class HomeActivity : AppCompatActivity() {

    lateinit var viewModelHome: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        val rankingRepository = HomeRepository()
        val viewModelProviderFactory = HomeViewModelProviderFactory(rankingRepository)
        viewModelHome = ViewModelProvider(this, viewModelProviderFactory).get(HomeViewModel::class.java)

    }

    private suspend fun getGuides(){
        RetrofitInstance.retrofit.getGuides()
    }
}