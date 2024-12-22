package com.example.wowlucky.LoginAndForgotPasswordFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reset_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResetPasswordBinding.bind(view)
        val navController = findNavController()
        binding.etEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!p0.toString().contains("@")) {
                    binding.etEmail.setBackgroundResource(R.drawable.red_input)
                } else {
                    binding.etEmail.setBackgroundResource(R.drawable.input)
                    binding.ivContinue.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_gradient_30dp_radius))
                    binding.btnContinue.isEnabled = true
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.btnContinue.setOnClickListener {
            val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToEnterConfirmationCodeFragment()
            navController.navigate(action)
        }
        binding.imageView.setOnClickListener {
            val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToLoginFragment()
            navController.navigate(action)
        }
    }
}