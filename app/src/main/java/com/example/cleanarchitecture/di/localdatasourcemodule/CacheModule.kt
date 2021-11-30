package com.example.cleanarchitecture.di.localdatasourcemodule

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.data.localDb.TokenDao
import com.example.cleanarchitecture.data.localDb.TokenDatabase
import com.example.cleanarchitecture.data.localDb.UserToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CacheModule {

    @Provides
    @Singleton
    fun provideTokenDb(@ApplicationContext context: Context): TokenDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TokenDatabase::class.java,
            TokenDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenDao(tokenDatabase: TokenDatabase): TokenDao {
        return tokenDatabase.tokenDao()
    }

}