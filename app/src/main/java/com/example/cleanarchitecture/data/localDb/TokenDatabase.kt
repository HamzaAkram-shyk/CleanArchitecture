package com.example.cleanarchitecture.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserToken::class], version = 1)
abstract class TokenDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao

    companion object {
        val DATABASE_NAME: String = "TokenTable"
    }
}