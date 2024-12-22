package com.example.wowlucky.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment() {
    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!
    private var isProfile = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyProfileBinding.bind(view)
        val navController = findNavController()
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