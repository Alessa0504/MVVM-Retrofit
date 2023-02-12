package com.example.mvvm_retrofit.model

import com.google.gson.annotations.SerializedName

/**
 * @Description: Item json格式对应数据格式
 * @author zouji
 * @date 2023/2/10
 */
// {"id":"0","author":"Alejandro Escamilla","width":5000,"height":3333,"url":"https://unsplash.com/photos/yC-Yzbqy7PY","download_url":"https://picsum.photos/id/0/5000/3333"}
data class DataItem(
    val id: String,
    val author: String,
    val url: String,
    val width: Int,
    val height: Int,
    @SerializedName("download_url") val downloadUrl: String   //重命名，用@SerializedName标记json字段原本的名字
)
