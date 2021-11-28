package com.example.cleanarchitecture.di.remotedatasourcemodule

import com.example.cleanarchitecture.data.api.NewsAPIService
import com.example.cleanarchitecture.util.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Moduller {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideNewsApiService(gson: Gson): NewsAPIService {
        return Retrofit.Builder()
            .baseUrl(Constant.base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsAPIService::class.java)
    }

}