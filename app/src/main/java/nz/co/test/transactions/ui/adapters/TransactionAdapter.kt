package nz.co.test.transactions.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.databinding.ItemTransactionBinding

class TransactionAdapter(private val openTransacDetails: (Transaction) -> Unit) : RecyclerView.Adapter<TransactionAdapter.TransactionItemViewHolder>() {

    private var transactionList = emptyList<Transaction>()

    inner class TransactionItemViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionItemViewHolder {
        val binding = ItemTransactionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionItemViewHolder(binding)
    }

    //Using viewbinding here
    override fun onBindViewHolder(holder: TransactionItemViewHolder, position: Int) {
        with(holder) {
            with(transactionList[position]) {
                binding.transactionCredit.text = "Credit:" + credit.setScale(2).toPlainString()
                binding.transactionSummary.text = summary
                binding.transactionDebit.text = "Debit:" + debit.setScale(2).toPlainString()
                binding.transactionDate.text = transactionDate
                binding.transactionItemContainer.setOnClickListener {
                    openTransacDetails(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(itemList: List<Transaction>) {
        transactionList = itemList
        notifyDataSetChanged()
    }

}