package com.example.cleanarchitecture.di.authdatasourcemodule

import com.example.cleanarchitecture.data.repository.authmodule.AuthDataSource
import com.example.cleanarchitecture.data.repository.authmodule.AuthDataSourceImp
import com.example.cleanarchitecture.domain.Implementation.AuthRepositoryImp
import com.example.cleanarchitecture.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceDependency {

    @Binds
    abstract fun bindAuthDataSource(authDataSource: AuthDataSourceImp): AuthDataSource

    @Binds
    abstract fun bindAuthRepository(authRepositoryImp: AuthRepositoryImp): AuthRepository

}