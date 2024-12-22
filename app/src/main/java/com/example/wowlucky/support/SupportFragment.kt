package com.example.wowlucky.support

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentSupportBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SupportFragment : Fragment() {
    private var _binding: FragmentSupportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_support, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSupportBinding.bind(view)
        val navController = findNavController()
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.VISIBLE
        binding.frameLayout1.setOnClickListener {
            val action = SupportFragmentDirections.actionSupportFragmentToFaqFragment("support")
            navController.navigate(action)
        }
        binding.frameLayout2.setOnClickListener {
            val action = SupportFragmentDirections.actionSupportFragmentToChatFragment()
            navController.navigate(action)
        }
        binding.imageView.setOnClickListener {
            val action = SupportFragmentDirections.actionSupportFragmentToProfileFragment()
            navController.navigate(action)
        }
    }
}