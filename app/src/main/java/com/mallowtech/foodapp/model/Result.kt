package com.mallowtech.foodapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FoodsTable")
data class Result(

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("link")
    @Expose
    var link: String? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
   /* @SerializedName("relevance")
    @Expose
    var relevance: Int? = null,*/
    @SerializedName("content")
    @Expose
    var content: String? = null,
   /* @SerializedName("dataPoints")
    @Expose
    var dataPoints: ArrayList<String> = arrayListOf()
*/
)