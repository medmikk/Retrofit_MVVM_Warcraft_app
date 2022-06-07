package com.medvedev.wowproject.api

import com.medvedev.wowproject.model.GuideResponce
import com.medvedev.wowproject.model.RankingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ClientAPI {

    @GET("api/guide/all")
    suspend fun getGuides(

    ): Response<GuideResponce>

    @GET("api/ranking/all")
    suspend fun getRanking(

    ): Response<RankingResponse>
}