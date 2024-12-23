package com.example.wowlucky.registrate

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentCreateAccountBinding
import com.google.android.material.textfield.TextInputEditText

class CreateAccountFragment : Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateAccountBinding.bind(view)
        val navController = findNavController()
        binding.etPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
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
                    binding.ivContinue.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_gradient_30dp_radius))
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
        binding.btnContinue.setOnClickListener {
            val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToLoginFragment()
            navController.navigate(action)
        }
        binding.imageView.setOnClickListener {
            val action = CreateAccountFragmentDirections.actionCreateAccountFragmentToRegistrationInactiveFragmentFragment()
            navController.navigate(action)
        }
    }

    private fun togglePasswordVisibility(button: ImageButton, etNewPassword: TextInputEditText) {
        if (etNewPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            etNewPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            button.setBackgroundResource(R.drawable.close_eye)
        } else {
            etNewPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            button.setBackgroundResource(R.drawable.open_eye)
        }
    }
}