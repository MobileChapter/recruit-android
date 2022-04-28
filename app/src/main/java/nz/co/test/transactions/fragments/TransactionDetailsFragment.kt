package nz.co.test.transactions.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nz.co.test.transactions.R
import nz.co.test.transactions.databinding.FragmentTransactionDetailsBinding
import nz.co.test.transactions.databinding.FragmentTransactionListBinding

class TransactionDetailsFragment : Fragment() {

    private lateinit var binding : FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false);
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransactionDetailsFragment()
    }
}