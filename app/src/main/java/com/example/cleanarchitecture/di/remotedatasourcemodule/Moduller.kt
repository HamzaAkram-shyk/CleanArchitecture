package com.example.cleanarchitecture.di.remotedatasourcemodule

import android.net.ConnectivityManager
import com.example.cleanarchitecture.data.api.NewsAPIService
import com.example.cleanarchitecture.di.NewsApiService
import com.example.cleanarchitecture.di.NewsApiServiceWithJson
import com.example.cleanarchitecture.util.ConnectivityInterceptor
import com.example.cleanarchitecture.util.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
    @NewsApiServiceWithJson
    fun provideNewsApiService(gson: Gson): NewsAPIService {
        return Retrofit.Builder()
            .baseUrl(Constant.base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NewsAPIService::class.java)
    }


    @Singleton
    @Provides
    fun provideHttpClient(connectivityManager: ConnectivityManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(connectivityManager))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.base_url)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    @NewsApiService
    fun provideApiService(retrofit: Retrofit): NewsAPIService {
        return retrofit.create(NewsAPIService::class.java)
    }

}