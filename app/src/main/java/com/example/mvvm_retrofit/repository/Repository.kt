package com.example.mvvm_retrofit.repository

import com.example.mvvm_retrofit.api.ApiService

/**
 * @Description:
 * @author zouji
 * @date 2023/2/10
 */
class Repository {
    suspend fun getPictures() = ApiService.create().getPictures()
}