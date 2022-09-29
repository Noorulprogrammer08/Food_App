package com.mallowtech.foodapp.model

data class SearchResult(
    val name: String,
    val foodLists: List<FoodList>,
    val totalResults: Int,
    val totalResultsVariants: Int,
    val type: String
)