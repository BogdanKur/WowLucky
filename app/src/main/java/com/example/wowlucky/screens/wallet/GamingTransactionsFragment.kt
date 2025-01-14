package com.example.wowlucky.screens.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.utils.BlurUtils.applyBlur
import com.example.wowlucky.screens.utils.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGamingTransactionsBinding
import com.example.wowlucky.screens.wallet.adapter.TransactionInterface
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets
import com.example.wowlucky.screens.wallet.adapter.GamingTransactionsAdapter
import com.example.wowlucky.screens.wallet.adapter.Transaction
import com.example.wowlucky.screens.wallet.GamingTransactionsFragmentDirections

class GamingTransactionsFragment : Fragment(), TransactionInterface {
    private lateinit var binding: FragmentGamingTransactionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamingTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeBlur(binding.root)
        val navController = findNavController()
        val adapter = GamingTransactionsAdapter(this)
        binding.rvTransaction.adapter = adapter
        val listAdapters = listOf(
            Transaction.DateMessage("17.11.2024"),
            Transaction.GameMessage("Game N25", "Win 4/4 tours", "Emma Roberts", "Mr zero", "synopsus", "349", "349", "349")
        )
        adapter.submitList(listAdapters)
        binding.imageView.setOnClickListener {
            val action = GamingTransactionsFragmentDirections.actionGameTransactionsFragmentToProfileFragment()
            navController.navigate(action)
        }
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
    }

    override fun onButtonClick() {
        applyBlur(requireContext(), binding.rootLayout)
        val action = GamingTransactionsFragmentDirections.actionGameTransactionsFragmentToFiltrationFragment()
        findNavController().navigate(action)
    }

}