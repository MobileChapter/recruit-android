package nz.co.test.transactions.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.usecases.GettingTransactionsListUseCase
import nz.co.test.transactions.util.Resource
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val gettingTransactionsListUseCase: GettingTransactionsListUseCase
) : ViewModel() {

    private val _transactionStateLiveData = MutableLiveData<TransactionsListState>()
    val transactionStateLiveData
        get() = _transactionStateLiveData

    init {
        getAllTransactions()
    }

    private fun getAllTransactions() {
        gettingTransactionsListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _transactionStateLiveData.value =
                        TransactionsListState(transactions = result.data ?: emptyList())
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
    val transactions: List<Transaction> = emptyList(),
    val error: String = ""
)