package nz.co.test.transactions.data.services

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Transaction(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "transactionDate") val transactionDate: String,
    @field:Json(name = "summary") val summary: String,
    @field:Json(name = "debit") val debit: BigDecimal,
    @field:Json(name = "credit") val credit: BigDecimal
) : Parcelable