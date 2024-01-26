package kso.android.kotlinflow.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kso.android.kotlinflow.R
import kso.android.kotlinflow.ui.completion.CompletionActivity
import kso.android.kotlinflow.ui.errorhandling.catch.CatchActivity
import kso.android.kotlinflow.ui.errorhandling.emitall.EmitAllActivity
import kso.android.kotlinflow.ui.filter.FilterActivity
import kso.android.kotlinflow.ui.flowon.FlowOnActivity
import kso.android.kotlinflow.ui.map.MapActivity
import kso.android.kotlinflow.ui.reduce.ReduceActivity
import kso.android.kotlinflow.ui.retrofit.parallel.ParallelNetworkCallsActivity
import kso.android.kotlinflow.ui.retrofit.series.SeriesNetworkCallsActivity
import kso.android.kotlinflow.ui.retrofit.single.SingleNetworkCallActivity
import kso.android.kotlinflow.ui.retry.RetryActivity
import kso.android.kotlinflow.ui.retryexponentialbackoff.RetryExponentialBackoffActivity
import kso.android.kotlinflow.ui.retrywhen.RetryWhenActivity
import kso.android.kotlinflow.ui.room.RoomDBActivity
import kso.android.kotlinflow.ui.search.SearchActivity
import kso.android.kotlinflow.ui.task.onetask.LongRunningTaskActivity
import kso.android.kotlinflow.ui.task.twotasks.TwoLongRunningTasksActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
    }

    fun startParallelNetworkCallsActivity(view: View) {
        startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
    }

    fun startRoomDatabaseActivity(view: View) {
        startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
    }

    fun startCatchActivity(view: View) {
        startActivity(Intent(this@MainActivity, CatchActivity::class.java))
    }

    fun startEmitAllActivity(view: View) {
        startActivity(Intent(this@MainActivity, EmitAllActivity::class.java))
    }

    fun startCompletionActivity(view: View) {
        startActivity(Intent(this@MainActivity, CompletionActivity::class.java))
    }

    fun startLongRunningTaskActivity(view: View) {
        startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
    }

    fun startTwoLongRunningTasksActivity(view: View) {
        startActivity(Intent(this@MainActivity, TwoLongRunningTasksActivity::class.java))
    }

    fun startFilterActivity(view: View) {
        startActivity(Intent(this@MainActivity, FilterActivity::class.java))
    }

    fun startMapActivity(view: View) {
        startActivity(Intent(this@MainActivity, MapActivity::class.java))
    }

    fun startReduceActivity(view: View) {
        startActivity(Intent(this@MainActivity, ReduceActivity::class.java))
    }

    fun startSearchActivity(view: View) {
        startActivity(Intent(this@MainActivity, SearchActivity::class.java))
    }

    fun startRetryActivity(view: View) {
        startActivity(Intent(this@MainActivity, RetryActivity::class.java))
    }

    fun startRetryWhenActivity(view: View) {
        startActivity(Intent(this@MainActivity, RetryWhenActivity::class.java))
    }

    fun startRetryExponentialBackoffActivity(view: View) {
        startActivity(Intent(this@MainActivity, RetryExponentialBackoffActivity::class.java))
    }

    fun startFlowOnActivity(view: View) {
        startActivity(Intent(this@MainActivity, FlowOnActivity::class.java))
    }

}
