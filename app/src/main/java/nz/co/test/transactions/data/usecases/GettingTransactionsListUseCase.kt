package nz.co.test.transactions.data.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nz.co.test.transactions.data.repos.TransactionRepository
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GettingTransactionsListUseCase @Inject constructor(
    private val repository: TransactionRepository
) {

    companion object {
        const val DEFAULT_HTTPEXC_MSG = "An unexpected error occured"
        const val DEFAULT_IOEXC_MSG = "Couldn't reach server"
    }

    //To call use case as a function
    operator fun invoke () : Flow<Resource<List<Transaction>>> = flow {
        try {
            emit(Resource.Loading())
            val transactions = repository.getTransactions()
            emit(Resource.Success(transactions))
        } catch (e:HttpException) {
            emit(Resource.Error(e.localizedMessage ?: DEFAULT_HTTPEXC_MSG))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: DEFAULT_IOEXC_MSG))
        }
    }
}