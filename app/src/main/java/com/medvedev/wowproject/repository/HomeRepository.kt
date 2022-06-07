package com.medvedev.wowproject.repository

import com.medvedev.wowproject.api.RetrofitInstance
import retrofit2.Response

class HomeRepository {
    suspend fun getGuides() =
        RetrofitInstance.retrofit.getGuides()


    suspend fun getRanking() =
        RetrofitInstance.retrofit.getRanking()

}