//package com.example.mvvm_retrofit.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.mvvm_retrofit.api.ApiService
//import com.example.mvvm_retrofit.model.DataItem
//import com.example.mvvm_retrofit.repository.AppRepository
//
///**
// * @Description:
// * @author zouji
// * @date 2023/2/10
// */
//class MyPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
//
//    /**
//     * 暂时用不到，直接返回null
//     * @param state PagingState<Int, Repo>
//     * @return Int?
//     */
//    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
//        return try {
//            val page = params.key ?: 1  //当前页数
//            val pageSize = params.loadSize  //每一页包含多少条数据
//            val response = apiService.getPictures(page, pageSize)
////            val response = appRepository.getPictures(page, pageSize)
//            val data = response.body() ?: emptyList()  // todo?
//            val prePage = if (page > 1) page - 1 else null
//            val nextPage = if (data.isNotEmpty()) page + 1 else null
//            LoadResult.Page(data, prePage, nextPage)   //构建LoadResult对象并返回
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}