package com.mallowtech.foodapp.common

interface FoodAppViewModelCallBack {
    fun <T> onSuccess(data: T)
    fun onError(errors: GetFoodListErrorResponse)
    fun onFailure(failureMessage: String?)
    fun <T> onNoNetwork(localData: T)
}