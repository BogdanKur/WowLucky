package com.example.wowlucky.registrate

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentRegistrationInactiveBinding

class RegistrationInactiveFragment : Fragment() {
    private var _binding: FragmentRegistrationInactiveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration_inactive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegistrationInactiveBinding.bind(view)
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