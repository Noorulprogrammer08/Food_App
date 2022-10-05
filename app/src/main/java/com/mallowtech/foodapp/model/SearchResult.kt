package com.mallowtech.foodapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,
    @SerializedName("totalResultsVariants")
    @Expose
    var totalResultsVariants: Int? = null,
    @SerializedName("results")
    @Expose
    var results: ArrayList<Result> = arrayListOf()
)