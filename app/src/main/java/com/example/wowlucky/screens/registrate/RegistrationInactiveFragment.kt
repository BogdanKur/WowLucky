package com.example.wowlucky.screens.registrate

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentRegistrationInactiveBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class RegistrationInactiveFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationInactiveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationInactiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        val navController = findNavController()
        val text = getString(R.string.have_an_account)
        val spannableText = SpannableString(text)
        val start = text.indexOf("LOG IN")
        val end = start + "LOG IN".length
        spannableText.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.btnHaveAnAccount.text = spannableText
        binding.checkbox.setOnClickListener {
            if(binding.checkbox.isChecked) {
                binding.ivGoogle.setImageResource(R.drawable.google_icon)
                binding.btnSignUpEmail.isClickable = true
                binding.btnSignUpGoogle.isClickable = true
                binding.btnBackgroundEmail.setBackgroundResource(R.drawable.btn_gradient_30dp_radius)
                binding.btnBackgroundGoogle.setBackgroundResource(R.drawable.btn_gradient_30dp_radius)
            } else {
                binding.ivGoogle.setImageResource(R.drawable.google_icon_inactive)
                binding.btnSignUpEmail.isClickable = false
                binding.btnSignUpGoogle.isClickable = false
                binding.btnBackgroundEmail.setBackgroundResource(R.drawable.frame_62)
                binding.btnBackgroundGoogle.setBackgroundResource(R.drawable.frame_62)
            }
        }
        binding.btnSignUpEmail.setOnClickListener {
            if(binding.checkbox.isChecked) {
                val action = RegistrationInactiveFragmentDirections.actionRegistrationInactiveFragmentToCreateAccountFragment()
                navController.navigate(action)
            }
        }
        binding.btnHaveAnAccount.setOnClickListener {
            val action = RegistrationInactiveFragmentDirections.actionRegistrationInactiveFragmentToLoginFragment()
            navController.navigate(action)
        }
    }
}