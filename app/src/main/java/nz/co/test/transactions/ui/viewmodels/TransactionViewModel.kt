package nz.co.test.transactions.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.usecases.GettingTransactionsListUseCase
import nz.co.test.transactions.util.Resource
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val gettingTransactionsListUseCase: GettingTransactionsListUseCase
) : ViewModel() {

    init {
        getAllTransactions()
    }

    private val _transactionListLiveData = MutableLiveData<Array<Transaction>>()
    val transactionListLiveData: LiveData<Array<Transaction>> = _transactionListLiveData

    private val _transactionStateLiveData = MutableLiveData<TransactionsListState>()
    val transactionStateLiveData: LiveData<TransactionsListState> = _transactionStateLiveData

    private fun getAllTransactions() {
        gettingTransactionsListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _transactionStateLiveData.value =
                        TransactionsListState(transactions = result.data ?: emptyArray())
                }
                is Resource.Error -> {
                    _transactionStateLiveData.value =
                        TransactionsListState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _transactionStateLiveData.value = TransactionsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class TransactionsListState(
    val isLoading: Boolean = false,
    val transactions: Array<Transaction> = emptyArray(),
    val error: String = ""
)