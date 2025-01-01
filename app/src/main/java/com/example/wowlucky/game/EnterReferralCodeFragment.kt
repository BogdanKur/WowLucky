package com.example.wowlucky.game

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
import com.example.wowlucky.doOnApplyWindowInsets

class EnterReferralCodeFragment : Fragment() {
    private var _binding: FragmentEnterReferralCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_referral_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEnterReferralCodeBinding.bind(view)
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