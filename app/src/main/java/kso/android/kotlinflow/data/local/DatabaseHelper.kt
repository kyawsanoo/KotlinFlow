package kso.android.kotlinflow.data.local

import kotlinx.coroutines.flow.Flow
import kso.android.kotlinflow.data.local.entity.User

interface DatabaseHelper {

    fun getUsers(): Flow<List<User>>

    fun insertAll(users: List<User>): Flow<Unit>

}