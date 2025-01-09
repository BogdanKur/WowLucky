package com.example.wowlucky.screens.LoginAndForgotPasswordFragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.LoginAndForgotPasswordFragment.LoginFragmentDirections
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentLoginBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private var isPasswordVisible = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            val imeVisible = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom > 0
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updatePadding(
                top = rect.top + systemBarsInsets.top,
                bottom = rect.bottom + if (imeVisible) {
                    insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                } else {
                    systemBarsInsets.bottom
                }
            )
            insets
        }

        binding.btnPassVisible.setOnClickListener {
            togglePasswordVisibility(binding.btnPassVisible, binding.etPassword)
        }
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString()
                if (!isValidEmail(email)) {
                    binding.etEmail.setBackgroundResource(R.drawable.red_input)
                    binding.tvError.visibility = View.VISIBLE
                } else {
                    binding.etEmail.setBackgroundResource(R.drawable.input)
                    binding.ivContinue.setBackgroundResource(R.drawable.btn_gradient_30dp_radius)
                    binding.btnContinue.isEnabled = true
                    binding.tvError.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}

            private fun isValidEmail(email: String): Boolean {
                val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
                return email.matches(Regex(emailPattern))
            }
        })
        binding.tvForgotPassword.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment()
            navController.navigate(action)
        }
        binding.imageView.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationInactiveFragment()
            navController.navigate(action)
        }
        binding.btnContinue.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
            navController.navigate(action)
        }
    }

    private fun togglePasswordVisibility(button: ImageButton, etNewPassword: TextInputEditText) {
        val typeface: Typeface = resources.getFont(R.font.agrandir)
        isPasswordVisible = !isPasswordVisible
        if (isPasswordVisible) {
            etNewPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            button.setImageResource(R.drawable.open_eye)
            etNewPassword.typeface = typeface
        } else {
            etNewPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            button.setImageResource(R.drawable.close_eye)
            etNewPassword.typeface = typeface
        }
        etNewPassword.setSelection(etNewPassword.text?.length ?: 0)
    }

}