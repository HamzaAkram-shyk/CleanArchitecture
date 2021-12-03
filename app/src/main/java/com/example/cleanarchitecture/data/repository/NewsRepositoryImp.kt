package com.example.cleanarchitecture.data.repository

import android.content.Context
import android.util.Log
import com.example.cleanarchitecture.data.localDb.UserToken
import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.data.repository.localdatasource.NewsLocalDataSource
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class NewsRepositoryImp @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource,

    ) :
    NewsRepository {

    override suspend fun getNewsHeadlines(page: Int, country: String): Resource<APIResponse> {
        return try {
            val response = newsRemoteDataSource.getTopHeadlines(page, country)
            Resource.Success(response.body()!!)
        } catch (e: Exception) {
            Resource.Error("${e.localizedMessage.toString()}")
        }
    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun savedNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryNews(category: String): Resource<APIResponse> {
        return try {
            newsRemoteDataSource.getCategoryNews(category).toResource()
        } catch (e: Exception) {

            Resource.Error("${e.localizedMessage.toString()}")
        }

    }

    override suspend fun registerUserToken(userToken: UserToken): Long {
        return newsLocalDataSource.registeredUserToken(userToken)
    }

    override suspend fun getNews(category: String): Flow<APIResponse> {
        return flow {
            emit(newsRemoteDataSource.getCategoryNews(category).body()!!)
        }
    }


}

fun <T> Response<T>.toResource(): Resource<T> {
    val response = this
    if (response.isSuccessful) {
        response.body()?.let { result ->
            return Resource.Success(result)
        }
    }
    return Resource.Error(response.code().toString())
}