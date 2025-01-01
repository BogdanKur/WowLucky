package com.example.wowlucky.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentWithdrawalsBinding
import com.example.wowlucky.doOnApplyWindowInsets

class WithdrawalsFragment : Fragment() {
    private var _binding: FragmentWithdrawalsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_withdrawals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWithdrawalsBinding.bind(view)
        val navController = findNavController()
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
        binding.imageView.setOnClickListener {
            val action = WithdrawalsFragmentDirections.actionWithdrawalsFragmentToProfileFragment()
            navController.navigate(action)
        }
    }

}