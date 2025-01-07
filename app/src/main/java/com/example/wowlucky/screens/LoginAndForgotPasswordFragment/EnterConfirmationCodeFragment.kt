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
import com.example.wowlucky.LoginAndForgotPasswordFragment.EnterConfirmationCodeFragmentDirections
import com.example.wowlucky.screens.utils.BlurUtils.applyBlur
import com.example.wowlucky.screens.utils.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentEnterConfimationCodeBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets
import com.google.android.material.textfield.TextInputEditText

class EnterConfirmationCodeFragment : Fragment() {
    private var _binding: FragmentEnterConfimationCodeBinding? = null
    private val binding get() = _binding!!
    private var isPasswordVisible = false
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
        binding.btnPassEnterVisible.setOnClickListener {
            togglePasswordVisibility(binding.btnPassEnterVisible, binding.etEnterPassword)
        }
        binding.btnPassRepeatVisible.setOnClickListener {
            togglePasswordVisibility(binding.btnPassRepeatVisible, binding.etRepeatPassword)
        }
        binding.btnContinue.setOnClickListener {
            if(binding.llPassword.visibility == View.GONE) {
                binding.linearLayout.visibility = View.GONE
                binding.llPassword.visibility = View.VISIBLE
            } else {
                applyBlur(requireContext(), binding.rootLayout)
                val action = EnterConfirmationCodeFragmentDirections.actionEnterConfirmationCodeFragmentToContinueToLoginFragment()
                navController.navigate(action)
            }
        }

        setupTextWatchers()

        binding.imageView.setOnClickListener {
            val action = EnterConfirmationCodeFragmentDirections.actionEnterConfirmationCodeFragmentToResetPasswordFragment()
            navController.navigate(action)
        }
    }

    private fun setupTextWatchers() {
        val editTexts = listOf(
            binding.textInputEditText1,
            binding.textInputEditText2,
            binding.textInputEditText3,
            binding.textInputEditText4,
            binding.textInputEditText5,
            binding.textInputEditText6
        )

        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    checkFields()

                    if (s.toString().length == 1 && i < editTexts.size - 1) {
                        editTexts[i + 1].requestFocus()
                    }

                    if (s.toString().isEmpty() && i > 0) {
                        editTexts[i - 1].requestFocus()
                    }
                }
            })
        }
    }

    private fun checkFields() {
        val isAllFilled = binding.textInputEditText1.text?.isNotBlank() == true &&
                binding.textInputEditText2.text?.isNotBlank() == true &&
                binding.textInputEditText3.text?.isNotBlank() == true &&
                binding.textInputEditText4.text?.isNotBlank() == true &&
                binding.textInputEditText5.text?.isNotBlank() == true &&
                binding.textInputEditText6.text?.isNotBlank() == true
        if (isAllFilled) {
            binding.ivContinue.setBackgroundResource(R.drawable.btn_gradient_30dp_radius)
            binding.btnContinue.isEnabled = true
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
