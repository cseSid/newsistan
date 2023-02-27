package com.sid.newsistan.dataClass

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    @SerializedName("status"       ) var status       : String?             = null,
    @SerializedName("totalResults" ) var totalResults : Int?                = null,
    @SerializedName("code"         ) var code : Int?                        = null,
    @SerializedName("message"      ) var message : String?                  = null,
    @SerializedName("articles"     ) var articles     : ArrayList<Articles> = arrayListOf()
) : Parcelable

@Parcelize
data class Source (

    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("name" ) var name : String? = null

) : Parcelable


@Entity(tableName = "news_table")
@Parcelize
data class Articles (

    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id"      ) var id      : Int? = null,

    @SerializedName("source"      ) var source      : Source? = Source(),
    @SerializedName("author"      ) var author      : String? = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("url"         ) var url         : String? = null,
    @SerializedName("urlToImage"  ) var urlToImage  : String? = null,
    @SerializedName("publishedAt" ) var publishedAt : String? = null,
    @SerializedName("content"     ) var content     : String? = null

) : Parcelable