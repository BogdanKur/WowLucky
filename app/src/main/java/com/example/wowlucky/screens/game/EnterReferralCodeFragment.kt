package com.example.wowlucky.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentEnterReferralCodeBinding
import com.example.wowlucky.screens.game.EnterReferralCodeFragmentDirections
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class EnterReferralCodeFragment : Fragment() {
    private lateinit var binding: FragmentEnterReferralCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterReferralCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.imageView.setOnClickListener {
            val action = EnterReferralCodeFragmentDirections.actionEnterReferralCodeFragmentToReferralCodeFragment()
            navController.navigate(action)
        }
        binding.floatButton.setOnClickListener {
            val action = EnterReferralCodeFragmentDirections.actionEnterReferralCodeFragmentToFaqFragment()
            navController.navigate(action)
        }
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
    }
}