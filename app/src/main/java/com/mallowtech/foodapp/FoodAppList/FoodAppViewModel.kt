package com.mallowtech.foodapp.FoodAppList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mallowtech.foodapp.common.FoodAppRepository
import com.mallowtech.foodapp.common.FoodAppViewModelCallBack
import com.mallowtech.foodapp.common.GetFoodListErrorResponse
import com.mallowtech.foodapp.common.SingleLiveEvent
import com.mallowtech.foodapp.model.Result

class FoodAppViewModel(application: Application) : AndroidViewModel(application) {

    val apiErrorResponse by lazy { SingleLiveEvent<String>() }
    val isNetworkConnected by lazy { SingleLiveEvent<String>() }

    fun getFoodDataBase(name: String, int: Int) {
        FoodAppRepository.getFoodList(name, object : FoodAppViewModelCallBack {
            override fun <T> onSuccess(data: T) {
            }

            override fun onError(errors: GetFoodListErrorResponse) {
                val errorResponse = errors as GetFoodListErrorResponse
                apiErrorResponse.value = errorResponse.statusMessage
            }

            override fun onFailure(failureMessage: String?) {
                apiErrorResponse.value = failureMessage
            }

            override fun <T> onNoNetwork(localData: T) {
                isNetworkConnected.value = false.toString()
            }

        })
    }

    fun loadDesc(): LiveData<List<Result?>?> {
        return FoodAppRepository.foodsDatabase?.foodAppDao()?.getPersonsSortByDescLastName()
            ?: MutableLiveData()
    }

    fun loadAsc(): LiveData<List<Result?>?> {
        return FoodAppRepository.foodsDatabase?.foodAppDao()?.getPersonsSortByAscLastName()
            ?: MutableLiveData()
    }
}
