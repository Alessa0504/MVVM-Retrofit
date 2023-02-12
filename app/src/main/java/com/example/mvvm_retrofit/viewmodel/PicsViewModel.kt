package com.example.mvvm_retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_retrofit.model.PicsResponse
import com.example.mvvm_retrofit.repository.Repository
import com.example.mvvm_retrofit.util.Resource
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @Description:
 * @author zouji
 * @date 2023/2/10
 */
class PicsViewModel(private val repository: Repository) : ViewModel() {

    companion object {
        const val NET_WORK_FAIL = "network_failure"
        const val CONVERSION_ERROR = "Conversion Error"
    }

    val picLiveData = MutableLiveData<Resource<PicsResponse>>()  //PicsResponse为data所属的类型T

    init {
        getPics()
    }

    private fun getPics() {
        viewModelScope.launch {
            fetchPics()
        }
    }

    /**
     * 获取图片
     */
    private suspend fun fetchPics() {
        picLiveData.postValue(Resource.Loading<PicsResponse>())  //postValue类型为Resource<T>，Resource.Loading<T>()的父类是Resource<T>，所以也满足类型Resource<T>
        try {
            val response = repository.getPictures()
            if (response.isSuccessful) {
                response.body()?.let {
                    picLiveData.postValue(Resource.Success(it))
                }
            } else {
                picLiveData.postValue(Resource.Error(null, message = response.message()))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> picLiveData.postValue(Resource.Error(null, message = NET_WORK_FAIL))
                else -> picLiveData.postValue(Resource.Error(null, message = CONVERSION_ERROR))
            }
        }
    }
}