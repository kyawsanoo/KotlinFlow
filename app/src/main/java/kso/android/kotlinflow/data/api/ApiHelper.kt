package kso.android.kotlinflow.data.api

import kotlinx.coroutines.flow.Flow
import kso.android.kotlinflow.data.model.ApiUser

interface ApiHelper {

    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>

}