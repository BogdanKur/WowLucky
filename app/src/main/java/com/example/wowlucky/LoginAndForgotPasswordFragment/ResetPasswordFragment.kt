package com.example.wowlucky.LoginAndForgotPasswordFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentResetPasswordBinding
import com.example.wowlucky.doOnApplyWindowInsets

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
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                bottom = rect.bottom + if (insets.getInsets(WindowInsetsCompat.Type.ime()).bottom == 0) {
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
                } else {
                    insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                },
            )
            insets
        }
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                if (!isValidEmail(email)) {
                    binding.etEmail.setBackgroundResource(R.drawable.red_input)
                } else {
                    binding.etEmail.setBackgroundResource(R.drawable.input)
                    binding.ivContinue.setBackgroundResource(R.drawable.btn_gradient_30dp_radius)
                    binding.btnContinue.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {}

            private fun isValidEmail(email: String): Boolean {
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                return email.matches(Regex(emailPattern))
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