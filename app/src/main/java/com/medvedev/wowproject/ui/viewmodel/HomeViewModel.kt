package com.medvedev.wowproject.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medvedev.wowproject.model.GuideResponce
import com.medvedev.wowproject.model.RankingResponse
import com.medvedev.wowproject.model.SpecDTO
import com.medvedev.wowproject.repository.HomeRepository
import com.medvedev.wowproject.util.Constants
import com.medvedev.wowproject.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit

class HomeViewModel (
    val homeRepository : HomeRepository
    ): ViewModel(){

    val guides: MutableLiveData<Resource<GuideResponce>> = MutableLiveData()
    var ranking: MutableLiveData<Resource<RankingResponse>> = MutableLiveData()
    var rankinglist: MutableLiveData<Resource<List<SpecDTO>>> = MutableLiveData()

    init {
        getRankingByModeTarget(Constants.Target.SINGLE, Constants.Mode.REAL)
        getGuides()
    }

    fun getGuides() = viewModelScope.launch {
        guides.postValue(Resource.Loading())
        val responce = homeRepository.getGuides()
        guides.postValue(handleGuides(responce))
    }

    fun getRanking() = viewModelScope.launch {
        ranking.postValue(Resource.Loading())
        val response = homeRepository.getRanking()
        ranking.postValue(handleRanking(response))
    }

    fun getRankingByModeTarget(target: Constants.Target, mode: Constants.Mode) = viewModelScope.launch {
        rankinglist.postValue(Resource.Loading())
        var targ = 1
        var mod = "real"

        if(target == Constants.Target.SINGLE)
            targ = 1
        else if (target == Constants.Target.MULTIPLE)
            targ = 3

        if(mode == Constants.Mode.MAX)
            mod = "max"
        else if (mode == Constants.Mode.REAL)
            mod = "real"

        val response = homeRepository.getRanking().body()?.get(0)?.specslist
        var newResponse = ArrayList<SpecDTO>()
        response?.let {
            for(spec in response){
                if (spec.mode == mod && spec.target == targ)
                    newResponse.add(spec)
            }
        }
        rankinglist.postValue(handleRankingList(newResponse))
    }

    private fun handleRankingList(response: List<SpecDTO>?) : Resource<List<SpecDTO>>{
        response?.let{ resultResponse ->
            return Resource.Success(resultResponse)
        }
        return Resource.Error("null array")
    }

    private fun handleRanking(response: Response<RankingResponse>) : Resource<RankingResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleGuides(response: Response<GuideResponce>) : Resource<GuideResponce>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}