package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.APIResponse
import com.example.cleanarchitecture.data.model.Article
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.util.Resource
import retrofit2.Response
import javax.inject.Inject


class NewsRepositoryImp @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRepository {

    override suspend fun getNewsHeadlines(page: Int, country: String): Resource<APIResponse> {
        return try {
            val response = newsRemoteDataSource.getTopHeadlines(page, country)
            Resource.Success(response.body()!!)
        } catch (e: Exception) {
            Resource.Error("${e.message.toString()}")
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
        return newsRemoteDataSource.getCategoryNews(category).toResource()
    }


}

fun <T> Response<T>.toResource(): Resource<T> {
    val response = this
    if (response.isSuccessful) {
        response.body()?.let { result ->
            return Resource.Success(result)
        }
    }
    return Resource.Error(response.message())
}