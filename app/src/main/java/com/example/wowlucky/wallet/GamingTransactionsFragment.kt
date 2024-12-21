package com.example.wowlucky.wallet

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGamingTransactionsBinding
import com.example.wowlucky.profile.FiltrationWithdrawalFragment

class GamingTransactionsFragment : Fragment(), TransactionInterface {
    private var _binding: FragmentGamingTransactionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gaming_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGamingTransactionsBinding.bind(view)
        val navController = findNavController()
        val adapter = GamingTransactionsAdapter(this)
        binding.rvTransaction.adapter = adapter
        val listAdapters = listOf(
            Transaction.DateMessage("17.11.2024"),
            Transaction.GameMessage("Game N25", "Win 4/4 tours", "Emma Roberts", "Mr zero", "synopsus", "349", "349", "349")
        )
        adapter.submitList(listAdapters)
        binding.imageView.setOnClickListener {
            val action = GamingTransactionsFragmentDirections.actionGameTransactionsFragmentToGamePageFragment()
            navController.navigate(action)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onButtonClick() {
        binding.rootLayout.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        val bottomSheet = FiltrationWithdrawalFragment()
        fragmentManager?.let { it1 -> bottomSheet.show(it1, "FiltrationWithdrawalFragment") }
        bottomSheet.dialog?.setOnDismissListener {
            binding.rootLayout.setRenderEffect(null)
        }
    }

}