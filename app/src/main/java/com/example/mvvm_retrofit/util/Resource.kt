package com.example.mvvm_retrofit.util

/**
 * @Description: 定义所有状态
 * @author zouji
 * @date 2023/2/11
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T?, message: String) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}
