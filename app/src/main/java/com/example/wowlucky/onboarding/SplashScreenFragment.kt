package com.example.wowlucky.onboarding

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashScreenBinding.bind(view)
        requireActivity().window.navigationBarColor = ContextCompat.getColor(requireContext(), R.color.black)
        val navController = findNavController()
        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashScreenFragmentDirections.actionSplashScreenFragmentToWelcomeFragment()
            navController.navigate(action)
        }, 2000)
    }
}