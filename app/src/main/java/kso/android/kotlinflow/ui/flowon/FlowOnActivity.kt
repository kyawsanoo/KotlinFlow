package kso.android.kotlinflow.ui.flowon

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kso.android.kotlinflow.R
import kso.android.kotlinflow.data.api.ApiHelperImpl
import kso.android.kotlinflow.data.api.RetrofitBuilder
import kso.android.kotlinflow.data.local.DatabaseBuilder
import kso.android.kotlinflow.data.local.DatabaseHelperImpl
import kso.android.kotlinflow.databinding.ActivityLongRunningTaskBinding
import kso.android.kotlinflow.ui.base.ViewModelFactory
import kso.android.kotlinflow.utils.DefaultDispatcherProvider

class FlowOnActivity : AppCompatActivity() {

    private lateinit var viewModel: FlowOnViewModel
    private lateinit var binding: ActivityLongRunningTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLongRunningTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTextView()
        setupViewModel()
        setupTask()
    }

    private fun setupTextView() {
        binding.progressBar.visibility = View.GONE
        binding.textView.text = getString(R.string.check_logcat)
        binding.textView.visibility = View.VISIBLE
    }

    private fun setupTask() {
        viewModel.startFlowOnTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[FlowOnViewModel::class.java]
    }
}
