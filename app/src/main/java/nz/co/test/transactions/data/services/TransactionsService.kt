package nz.co.test.transactions.data.services

import retrofit2.http.GET

interface TransactionsService {

    @GET("/4d73acaa7caa1167676445c922835554c5572e82/test-data.json")
    suspend fun retrieveTransactions(): Array<Transaction>

}

