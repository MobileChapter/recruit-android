package nz.co.test.transactions.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.usecases.GettingTransactionsListUseCase
import nz.co.test.transactions.util.MainCoroutineScopeRule
import nz.co.test.transactions.util.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TransactionViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var mockObserver: Observer<TransactionsListState>

    private lateinit var myViewModel: TransactionViewModel

    @Mock
    private lateinit var useCase: GettingTransactionsListUseCase

    @Mock
    private lateinit var transactionList: List<Transaction>

    private lateinit var viewState: TransactionsListState

    @Captor
    private lateinit var captor: ArgumentCaptor<TransactionsListState>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewState = TransactionsListState()
        useCase = GettingTransactionsListUseCase(FakeTransactionRepository())
        myViewModel = TransactionViewModel(useCase)
    }

    @Test
    fun onTransactionsRetrieved() {
        runBlocking {
            val flow = flow {
                emit(Resource.Loading())
                delay(10)
                emit(Resource.Success(transactionList))
            }

            `when`(useCase.invoke()).thenReturn(flow)
            val liveData = myViewModel.transactionStateLiveData
            liveData.observeForever(mockObserver)

            verify(mockObserver).onChanged(captor.capture())
            assertEquals(true, captor.value.isLoading)
            coroutineScope.advanceTimeBy(10)
            verify(mockObserver, times(2)).onChanged(captor.capture())
            assertEquals(transactionList, captor.value.transactions)
        }
    }
}
