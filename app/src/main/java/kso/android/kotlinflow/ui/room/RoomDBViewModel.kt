package kso.android.kotlinflow.ui.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kso.android.kotlinflow.data.api.ApiHelper
import kso.android.kotlinflow.data.local.DatabaseHelper
import kso.android.kotlinflow.data.local.entity.User
import kso.android.kotlinflow.utils.DispatcherProvider
import kso.android.kotlinflow.ui.base.UiState

class RoomDBViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<User>>> = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            dbHelper.getUsers()
                .flatMapConcat { usersFromDb ->
                    if (usersFromDb.isEmpty()) {
                        return@flatMapConcat apiHelper.getUsers()
                            .map { apiUserList ->
                                val userList = mutableListOf<User>()
                                for (apiUser in apiUserList) {
                                    val user = User(
                                        apiUser.id,
                                        apiUser.name,
                                        apiUser.email,
                                        apiUser.avatar
                                    )
                                    userList.add(user)
                                }
                                userList
                            }
                            .flatMapConcat { usersToInsertInDB ->
                                dbHelper.insertAll(usersToInsertInDB)
                                    .flatMapConcat {
                                        flow {
                                            emit(usersToInsertInDB)
                                        }
                                    }
                            }
                    } else {
                        return@flatMapConcat flow {
                            emit(usersFromDb)
                        }
                    }
                }
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}