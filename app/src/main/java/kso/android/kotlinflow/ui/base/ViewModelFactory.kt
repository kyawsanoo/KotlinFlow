package kso.android.kotlinflow.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kso.android.kotlinflow.data.api.ApiHelper
import kso.android.kotlinflow.data.local.DatabaseHelper
import kso.android.kotlinflow.ui.completion.CompletionViewModel
import kso.android.kotlinflow.ui.errorhandling.catch.CatchViewModel
import kso.android.kotlinflow.ui.errorhandling.emitall.EmitAllViewModel
import kso.android.kotlinflow.ui.filter.FilterViewModel
import kso.android.kotlinflow.ui.flowon.FlowOnViewModel
import kso.android.kotlinflow.ui.map.MapViewModel
import kso.android.kotlinflow.ui.reduce.ReduceViewModel
import kso.android.kotlinflow.ui.retrofit.parallel.ParallelNetworkCallsViewModel
import kso.android.kotlinflow.ui.retrofit.series.SeriesNetworkCallsViewModel
import kso.android.kotlinflow.ui.retrofit.single.SingleNetworkCallViewModel
import kso.android.kotlinflow.ui.retry.RetryViewModel
import kso.android.kotlinflow.ui.retryexponentialbackoff.RetryExponentialBackoffModel
import kso.android.kotlinflow.ui.retrywhen.RetryWhenViewModel
import kso.android.kotlinflow.ui.room.RoomDBViewModel
import kso.android.kotlinflow.ui.task.onetask.LongRunningTaskViewModel
import kso.android.kotlinflow.ui.task.twotasks.TwoLongRunningTasksViewModel
import kso.android.kotlinflow.utils.DispatcherProvider

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(CatchViewModel::class.java)) {
            return CatchViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(EmitAllViewModel::class.java)) {
            return EmitAllViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
            return LongRunningTaskViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(TwoLongRunningTasksViewModel::class.java)) {
            return TwoLongRunningTasksViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(CompletionViewModel::class.java)) {
            return CompletionViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(FilterViewModel::class.java)) {
            return FilterViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            return MapViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(ReduceViewModel::class.java)) {
            return ReduceViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RetryViewModel::class.java)) {
            return RetryViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RetryWhenViewModel::class.java)) {
            return RetryWhenViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(RetryExponentialBackoffModel::class.java)) {
            return RetryExponentialBackoffModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        if (modelClass.isAssignableFrom(FlowOnViewModel::class.java)) {
            return FlowOnViewModel(apiHelper, dbHelper, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}