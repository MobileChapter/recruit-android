package nz.co.test.transactions.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nz.co.test.transactions.databinding.FragmentTransactionDetailsBinding

class TransactionDetailsFragment : Fragment() {

    private lateinit var binding : FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        //retrieve transaction details
        //parse to UI
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransactionDetailsFragment()
    }
}