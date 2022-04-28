package nz.co.test.transactions.data.repos

import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.data.services.TransactionsService
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val api: TransactionsService
) : TransactionRepository {

    override suspend fun getTransactions(): List<Transaction> {
        return api.retrieveTransactions()
    }
}