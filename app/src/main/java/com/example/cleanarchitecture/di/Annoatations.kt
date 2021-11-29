package com.example.cleanarchitecture.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsApiService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsApiServiceWithJson