package com.example.dbapplication.dao

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}