package com.example.cleanarchitecture.data.localDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registeredUserToken(token: UserToken): Long

}