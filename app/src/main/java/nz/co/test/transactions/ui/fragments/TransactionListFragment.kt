package nz.co.test.transactions.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import nz.co.test.transactions.R
import nz.co.test.transactions.data.services.Transaction
import nz.co.test.transactions.databinding.FragmentTransactionListBinding
import nz.co.test.transactions.ui.adapters.TransactionAdapter
import nz.co.test.transactions.ui.viewmodels.TransactionViewModel

@AndroidEntryPoint
class TransactionListFragment : Fragment() {

    private val viewModel by viewModels<TransactionViewModel>()
    private lateinit var binding : FragmentTransactionListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val openTransactionDetails = { transaction: Transaction ->
            Navigation.findNavController(binding.root).navigate(R.id.actionShowTransactionDetails)
        }
        val adapter = TransactionAdapter(openTransactionDetails)
        binding.transactionList.adapter = adapter
        viewModel.transactionStateLiveData.observe(viewLifecycleOwner, Observer { state ->
            if(state.isLoading) {

            }
            if(state.error.isNotBlank()) {

            }
            if(state.transactions.isNotEmpty()) {
                adapter.setItems(state.transactions)
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = TransactionListFragment()
    }
}