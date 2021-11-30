package com.example.cleanarchitecture.data.localDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "TokenTable")
data class UserToken(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var userId: Int,
    @ColumnInfo(name = "user_token")
    var userToken: String
)

