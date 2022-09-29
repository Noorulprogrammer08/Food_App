package com.mallowtech.foodapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mallowtech.foodapp.model.FoodList
import com.mallowtech.foodapp.ApiSetup.RetrofitServiceInterface
import com.mallowtech.foodapp.ApiSetup.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodAppViewModel:ViewModel() {
    lateinit var liveDataList:MutableLiveData<List<FoodList>>

    init {
        liveDataList = MutableLiveData()
    }
    fun getLiveDataObserver():MutableLiveData<List<FoodList>>{
        return liveDataList
    }
    fun makeApiCall(){
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        val retrofitService = retrofitInstance.create(RetrofitServiceInterface::class.java)
        val call = retrofitService.getFoodsList()
        call.enqueue(object :Callback<List<FoodList>> {
            override fun onFailure(call: Call<List<FoodList>>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<List<FoodList>>,
                response: Response<List<FoodList>>
            ) {
                liveDataList.postValue(response.body())
            }
        })
    }

}