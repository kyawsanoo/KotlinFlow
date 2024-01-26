package kso.android.kotlinflow.ui.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kso.android.kotlinflow.data.api.ApiHelper
import kso.android.kotlinflow.data.local.DatabaseHelper
import kso.android.kotlinflow.ui.base.UiState
import kso.android.kotlinflow.utils.DispatcherProvider

class FilterViewModel(
    apiHelper: ApiHelper,
    dbHelper: DatabaseHelper,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)

    val uiState: StateFlow<UiState<String>> = _uiState

    fun startFilterTask() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            val result = mutableListOf<Int>()
            (1..5).asFlow()
                .filter {
                    it % 2 == 0
                }
                .toList(result)
            _uiState.value = UiState.Success(result.toString())
        }
    }


}