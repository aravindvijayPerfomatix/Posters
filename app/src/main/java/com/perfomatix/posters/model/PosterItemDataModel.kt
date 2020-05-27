package com.perfomatix.posters.model

import com.google.gson.annotations.SerializedName

/*
* Model data class
*
*/

data class PageData(
    val page: Page
)

data class Page(
    @SerializedName("content-items")
    val content_items: ContentItem,
    @SerializedName("page-num")
    val page_num: String,
    @SerializedName("page-size")
    val page_size: String,
    val title: String,
    @SerializedName("total-content-items")
    val total_content_items: String
)

data class ContentItem(
    val content: ArrayList<Content>
)

data class Content(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster-image")
    val poster_image: String
)