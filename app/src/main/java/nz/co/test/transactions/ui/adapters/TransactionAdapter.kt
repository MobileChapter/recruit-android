package nz.co.test.transactions.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.databinding.ItemTransactionBinding

class TransactionAdapter(private val openTransactionDetails: (Transaction) -> Unit) : RecyclerView.Adapter<TransactionAdapter.TransactionItemViewHolder>() {

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
                binding.transactionCredit.text = credit.toString()
                binding.transactionSummary.text = summary
                binding.transactionDebit.text = debit.toString()
                binding.transactionDate.text = transactionDate
                binding.transactionItemContainer.setOnClickListener { openTransactionDetails }
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