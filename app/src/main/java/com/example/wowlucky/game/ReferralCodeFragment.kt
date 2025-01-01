package com.example.wowlucky.game

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentReferralCodeBinding
import com.example.wowlucky.doOnApplyWindowInsets
import com.google.android.material.textfield.TextInputEditText

class ReferralCodeFragment : Fragment() {
    private var _binding: FragmentReferralCodeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_referral_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReferralCodeBinding.bind(view)
        val navController = findNavController()
        binding.floatButton.setOnClickListener{
            val action = ReferralCodeFragmentDirections.actionReferralCodeFragmentToFaqFragment()
            navController.navigate(action)
        }
        binding.ivBlogger.setOnClickListener {
            toggleTextViewVisibility(binding.ivBlogger, binding.tvOpinion)
        }
        binding.imageView.setOnClickListener {
            val action = ReferralCodeFragmentDirections.actionReferralCodeFragmentToProfileFragment()
            navController.navigate(action)
        }
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
    }

    private fun toggleTextViewVisibility(button: ImageView, tvOpinion: TextView) {
        if (tvOpinion.visibility == View.GONE) {
            tvOpinion.visibility = View.VISIBLE
            button.setBackgroundResource(R.drawable.chevron_up)
        } else {
            tvOpinion.visibility = View.GONE
            button.setBackgroundResource(R.drawable.chevron_down)
        }
    }
}