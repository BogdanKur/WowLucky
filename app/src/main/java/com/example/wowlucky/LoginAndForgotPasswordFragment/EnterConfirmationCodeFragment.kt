package com.example.wowlucky.LoginAndForgotPasswordFragment

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.BlurUtils.applyBlur
import com.example.wowlucky.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentEnterConfimationCodeBinding
import com.google.android.material.textfield.TextInputEditText

class EnterConfirmationCodeFragment : Fragment() {
    private var _binding: FragmentEnterConfimationCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_confimation_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEnterConfimationCodeBinding.bind(view)
        removeBlur(binding.root)
        val navController = findNavController()
        binding.etEnterPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        binding.etRepeatPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        binding.btnPassEnterVisible.setOnClickListener {
            togglePasswordVisibility(binding.btnPassEnterVisible, binding.etEnterPassword)
        }
        binding.btnPassRepeatVisible.setOnClickListener {
            togglePasswordVisibility(binding.btnPassRepeatVisible, binding.etRepeatPassword)
        }
        binding.btnContinue.setOnClickListener {
            if(binding.llPassword.visibility ==View.GONE) {
                binding.linearLayout.visibility = View.GONE
                binding.llPassword.visibility = View.VISIBLE
            } else {
                applyBlur(requireContext(), binding.rootLayout)
                val action = EnterConfirmationCodeFragmentDirections.actionEnterConfirmationCodeFragmentToContinueToLoginFragment()
                navController.navigate(action)
            }
        }
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                checkFields()
            }
        }
        binding.textInputEditText1.addTextChangedListener(textWatcher)
        binding.textInputEditText2.addTextChangedListener(textWatcher)
        binding.textInputEditText3.addTextChangedListener(textWatcher)
        binding.textInputEditText4.addTextChangedListener(textWatcher)
        binding.textInputEditText5.addTextChangedListener(textWatcher)
        binding.textInputEditText6.addTextChangedListener(textWatcher)

        binding.imageView.setOnClickListener {
            val action = EnterConfirmationCodeFragmentDirections.actionEnterConfirmationCodeFragmentToResetPasswordFragment()
            navController.navigate(action)
        }
    }

    private fun checkFields() {
        val isAllFilled = binding.textInputEditText1.text?.isNotBlank() == true &&
                binding.textInputEditText2.text?.isNotBlank() == true &&
                binding.textInputEditText3.text?.isNotBlank() == true &&
                binding.textInputEditText4.text?.isNotBlank() == true &&
                binding.textInputEditText5.text?.isNotBlank() == true &&
                binding.textInputEditText6.text?.isNotBlank() == true
        if(isAllFilled) {
            binding.ivContinue.setBackgroundResource(R.drawable.btn_gradient_30dp_radius)
            binding.btnContinue.isEnabled = true
        }
    }

    private fun togglePasswordVisibility(button: ImageButton, etNewPassword: TextInputEditText) {
        if (etNewPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            etNewPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            button.setBackgroundResource(R.drawable.close_eye)
        } else {
            etNewPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            button.setBackgroundResource(R.drawable.open_eye)
        }
    }
}