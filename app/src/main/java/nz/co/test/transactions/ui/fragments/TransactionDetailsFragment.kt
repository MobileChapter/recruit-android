package nz.co.test.transactions.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nz.co.test.transactions.R
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.databinding.FragmentTransactionDetailsBinding
import java.math.BigDecimal
import java.math.RoundingMode

class TransactionDetailsFragment : Fragment() {

    private lateinit var binding : FragmentTransactionDetailsBinding
    private val ONE_HUNDRED = BigDecimal(100)
    private val GST_AMOUNT = BigDecimal(15)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        val bundle = arguments
        val args = TransactionDetailsFragmentArgs.fromBundle(checkNotNull(bundle))
        setupUI(args.transactionDetails)
        return binding.root
    }

    private fun setupUI(transactionDetails: Transaction) {
        binding.transactionId.text = getString(R.string.transaction_id_format, transactionDetails.id.toString())
        binding.transactionSummaryText.text =transactionDetails.summary
        binding.transactionCreditTxt.text = getString(R.string.transaction_credit_format, transactionDetails.credit.setScale(2).toPlainString())
        binding.transactionDebitTxt.text = getString(R.string.transaction_debit_format, transactionDetails.debit.setScale(2).toPlainString())
        binding.transactionGST.text = getString(R.string.transaction_gst_format, calculateGST(transactionDetails))
    }

    private fun calculateGST(transactionDetails: Transaction): String {
        return if(transactionDetails.debit.compareTo(BigDecimal.ZERO) == 0)
            percentage(transactionDetails.credit)
        else percentage(transactionDetails.debit)
    }

    private fun percentage(base: BigDecimal): String {
        return base.multiply(GST_AMOUNT).divide(ONE_HUNDRED).setScale(2, RoundingMode.HALF_DOWN).toPlainString()
    }

    companion object {
        @JvmStatic fun newInstance() = TransactionDetailsFragment()
    }
}