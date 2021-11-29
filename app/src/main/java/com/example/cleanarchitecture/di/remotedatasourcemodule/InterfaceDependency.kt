package com.example.cleanarchitecture.di.remotedatasourcemodule

import com.example.cleanarchitecture.data.repository.NewsRepositoryImp
import com.example.cleanarchitecture.data.repository.datasource.NewsRemoteDataSource
import com.example.cleanarchitecture.data.repository.datasourceImpl.NewsRemoteDataSourceImp
import com.example.cleanarchitecture.data.repository.localdatasource.NewsLocalDataSource
import com.example.cleanarchitecture.data.repository.localdatasourceImp.NewsLocalDataSourceImp
import com.example.cleanarchitecture.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceDependency {

    @Binds
    abstract fun bindNewsRemoteDataSource(dataSource: NewsRemoteDataSourceImp): NewsRemoteDataSource

    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImp): NewsRepository

    @Binds
    abstract fun bindNewsLocalDataSource(localDataSource: NewsLocalDataSourceImp): NewsLocalDataSource

}