package kso.android.kotlinflow.single

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kso.android.kotlinflow.data.api.ApiHelper
import kso.android.kotlinflow.data.local.DatabaseHelper
import kso.android.kotlinflow.ui.retrofit.single.SingleNetworkCallViewModel
import kso.android.kotlinflow.data.model.ApiUser
import kso.android.kotlinflow.utils.DispatcherProvider
import kso.android.kotlinflow.utils.TestDispatcherProvider
import kso.android.kotlinflow.ui.base.UiState
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {

    @Mock
    private lateinit var apiHelper: ApiHelper

    @Mock
    private lateinit var databaseHelper: DatabaseHelper

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        runTest {
            doReturn(flowOf(emptyList<ApiUser>())).`when`(apiHelper).getUsers()
            val viewModel =
                SingleNetworkCallViewModel(apiHelper, databaseHelper, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<ApiUser>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(apiHelper).getUsers()
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<ApiUser>> {
                throw IllegalStateException(errorMessage)
            }).`when`(apiHelper).getUsers()

            val viewModel =
                SingleNetworkCallViewModel(apiHelper, databaseHelper, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(apiHelper).getUsers()
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }
}