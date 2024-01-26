package kso.android.kotlinflow.ui.retryexponentialbackoff

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kso.android.kotlinflow.data.api.ApiHelperImpl
import kso.android.kotlinflow.data.api.RetrofitBuilder
import kso.android.kotlinflow.data.local.DatabaseBuilder
import kso.android.kotlinflow.data.local.DatabaseHelperImpl
import kso.android.kotlinflow.databinding.ActivityRetryBinding
import kso.android.kotlinflow.utils.DefaultDispatcherProvider
import kso.android.kotlinflow.ui.base.UiState
import kso.android.kotlinflow.ui.base.ViewModelFactory

class RetryExponentialBackoffActivity : AppCompatActivity() {

    private lateinit var viewModel: RetryExponentialBackoffModel
    private lateinit var binding: ActivityRetryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetryBinding.inflate(layoutInflater)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.textView.text = it.data
                            binding.textView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.textView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@RetryExponentialBackoffActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        viewModel.startTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext)),
                DefaultDispatcherProvider()
            )
        )[RetryExponentialBackoffModel::class.java]
    }
}
