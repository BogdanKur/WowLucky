package com.example.wowlucky.screens.LoginAndForgotPasswordFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.LoginAndForgotPasswordFragment.EnterConfirmationCodeFragmentDirections
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueToLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ContinueToLoginFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentContinueToLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_continue_to_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContinueToLoginBinding.bind(view)
        val navController = findNavController()
        binding.btnContinue.setOnClickListener {
            val action = EnterConfirmationCodeFragmentDirections.actionEnterConfirmationCodeFragmentToLoginFragment()
            navController.navigate(action)
        }
    }

}