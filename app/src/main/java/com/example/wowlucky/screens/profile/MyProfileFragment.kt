package com.example.wowlucky.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentMyProfileBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class MyProfileFragment : Fragment() {
    private lateinit var binding: FragmentMyProfileBinding
    private var isProfile = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
        binding.imageView.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToProfileFragment()
            navController.navigate(action)
        }
        binding.imageView4.setOnClickListener {
            if(isProfile) {
                binding.imageView4.setBackgroundResource(R.drawable.earning_enable_off)
            } else {
                binding.imageView4.setBackgroundResource(R.drawable.earning_enable_on)
            }
            isProfile = !isProfile
        }
        binding.floatButton.setOnClickListener {
            val action = MyProfileFragmentDirections.actionMyProfileFragmentToFaqFragment()
            navController.navigate(action)
        }
    }
}