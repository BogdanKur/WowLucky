package com.example.wowlucky.screens.support

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentSupportBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class SupportFragment : Fragment() {
    private lateinit var binding: FragmentSupportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSupportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.frameLayout1.setOnClickListener {
            val navControllers = requireActivity().findNavController(R.id.nav_host_fragment)
            navControllers.navigate(R.id.faqFragment)
        }
        binding.frameLayout2.setOnClickListener {
            val navControllers = requireActivity().findNavController(R.id.nav_host_fragment)
            navControllers.navigate(R.id.chatFragment)
        }
        binding.imageView.setOnClickListener {
            val action = SupportFragmentDirections.actionSupportFragmentToProfileFragment()
            navController.navigate(action)
        }
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
    }
}