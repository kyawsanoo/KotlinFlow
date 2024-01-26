package kso.android.kotlinflow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kso.android.kotlinflow.data.local.dao.UserDao
import kso.android.kotlinflow.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}