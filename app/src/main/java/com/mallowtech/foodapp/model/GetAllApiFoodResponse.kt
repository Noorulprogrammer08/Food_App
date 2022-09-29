package com.mallowtech.foodapp.model

data class GetAllApiFoodResponse(
    val activeFilterOptions: List<Any>,
    val expires: Long,
    val filterMapping: FilterMapping,
    val filterOptions: List<Any>,
    val isStale: Boolean,
    val limit: Int,
    val offset: Int,
    val query: String,
    val searchResults: List<SearchResult>,
    val sorting: String,
    val totalResults: Int
)