package nz.co.test.transactions.data.repos

import nz.co.test.transactions.data.services.Transaction

interface TransactionRepository {
    suspend fun getTransactions() : Array<Transaction>
}