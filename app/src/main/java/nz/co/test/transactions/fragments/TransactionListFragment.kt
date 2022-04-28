package nz.co.test.transactions.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import nz.co.test.transactions.R
import nz.co.test.transactions.databinding.FragmentTransactionListBinding

class TransactionListFragment : Fragment() {

    private lateinit var binding : FragmentTransactionListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionListBinding.inflate(inflater, container, false);
        binding.buttonTest.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.actionShowTransactionDetails)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransactionListFragment()
    }
}