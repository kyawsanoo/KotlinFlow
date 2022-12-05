package me.amitshekhar.learn.kotlin.flow.ui.retrofit.single

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import me.amitshekhar.learn.kotlin.flow.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.flow.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.flow.data.model.ApiUser
import me.amitshekhar.learn.kotlin.flow.utils.DispatcherProvider
import me.amitshekhar.learn.kotlin.flow.utils.Resource
import me.amitshekhar.learn.kotlin.flow.utils.TestCoroutineRule
import me.amitshekhar.learn.kotlin.flow.utils.TestDispatcherProvider
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SingleNetworkCallViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

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
            doReturn(flowOf(emptyList<ApiUser>()))
                .`when`(apiHelper)
                .getUsers()
            val viewModel =
                SingleNetworkCallViewModel(apiHelper, databaseHelper, dispatcherProvider)
            viewModel.users.test {
                assertEquals(Resource.success(emptyList<List<ApiUser>>()), awaitItem())
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
            })
                .`when`(apiHelper)
                .getUsers()

            val viewModel =
                SingleNetworkCallViewModel(apiHelper, databaseHelper, dispatcherProvider)
            viewModel.users.test {
                assertEquals(
                    Resource.error<List<ApiUser>>(IllegalStateException(errorMessage).toString()),
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