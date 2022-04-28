package nz.co.test.transactions.data.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.co.test.transactions.data.repos.TransactionRepository
import nz.co.test.transactions.data.repos.TransactionRepositoryImpl
import nz.co.test.transactions.data.services.TransactionsService
import nz.co.test.transactions.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTransactionService(): TransactionsService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TransactionsService::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(api : TransactionsService) : TransactionRepository {
        return TransactionRepositoryImpl(api)
    }
}