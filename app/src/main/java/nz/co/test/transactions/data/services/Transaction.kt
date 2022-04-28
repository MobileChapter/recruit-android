package nz.co.test.transactions.data.services

import com.squareup.moshi.Json
import java.math.BigDecimal
import java.time.OffsetDateTime

data class Transaction(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "transactionDate") val transactionDate: String,
    @field:Json(name = "summary") val summary: String,
    @field:Json(name = "debit") val debit: BigDecimal,
    @field:Json(name = "credit") val credit: BigDecimal
)