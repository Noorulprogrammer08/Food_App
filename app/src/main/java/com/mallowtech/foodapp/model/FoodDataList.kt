package com.mallowtech.foodapp.model

import android.app.appsearch.SearchResults
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/*@Entity(tableName = "foodListApp")*/
data class FoodDataList(
    @SerializedName("sorting")
    var sorting: String? = null,
    @SerializedName("filterMapping")
    var filterMapping: FilterMapping? = FilterMapping(),
    @SerializedName("filterOptions")
    var filterOptions: ArrayList<String> = arrayListOf(),
    @SerializedName("activeFilterOptions")
    var activeFilterOptions: ArrayList<String> = arrayListOf(),
    @SerializedName("query")
    var query: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int? = null,
    @SerializedName("limit")
    var limit: Int? = null,
    @SerializedName("offset")
    var offset: Int? = null,
    @SerializedName("searchResults")
    var searchResults: ArrayList<SearchResult> = arrayListOf(),
   /* @SerializedName("expires")
    var expires: Int? = null,*/
    @SerializedName("isStale")
    var isStale: Boolean? = null
)