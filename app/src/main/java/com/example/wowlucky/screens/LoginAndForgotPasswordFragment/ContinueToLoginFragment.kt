package com.example.wowlucky.screens.LoginAndForgotPasswordFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.LoginAndForgotPasswordFragment.EnterConfirmationCodeFragmentDirections
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueToLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ContinueToLoginFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentContinueToLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContinueToLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.btnContinue.setOnClickListener {
            val action = EnterConfirmationCodeFragmentDirections.actionEnterConfirmationCodeFragmentToLoginFragment()
            navController.navigate(action)
        }
    }

}