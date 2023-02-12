package com.example.mvvm_retrofit.api

import com.example.mvvm_retrofit.model.PicsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @Description: Retrofit API对象
 * @author zouji
 * @date 2023/2/10
 */
interface ApiService {

    //API
//    @GET("v2/list")
//    suspend fun getPictures(
//        @Query("start") since: Int,  //since, perPage名字可以随便取
//        @Query("count") perPage: Int
//    ): Response<PicsResponse>

    //API
    @GET("v2/list")
    suspend fun getPictures(): Response<PicsResponse>

    // 直接在内部提供了RetrofitClient
    companion object {
        private const val BASE_URL = "https://picsum.photos/"
        fun create(): ApiService = run {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)   //传入API对象
        }
    }
}