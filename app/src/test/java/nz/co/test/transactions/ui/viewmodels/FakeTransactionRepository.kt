package nz.co.test.transactions.ui.viewmodels

import nz.co.test.transactions.data.repos.TransactionRepository
import nz.co.test.transactions.data.services.Transaction
import java.math.BigDecimal

class FakeTransactionRepository : TransactionRepository {

    override suspend fun getTransactions(): List<Transaction> {
        return listOf(
            Transaction(1, "16-07-2021T12:12:12", "Test", BigDecimal(123.12), BigDecimal(321.32)),
            Transaction(2, "16-07-2021T12:12:12", "Test1", BigDecimal(123.12), BigDecimal(321.32)),
            Transaction(3, "16-07-2021T12:12:12", "Test2", BigDecimal(123.12), BigDecimal(321.32))
        )
    }
}