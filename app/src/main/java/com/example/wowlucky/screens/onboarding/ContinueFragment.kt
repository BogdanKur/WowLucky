package com.example.wowlucky.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.utils.CustomPageTransformer
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueBinding
import com.example.wowlucky.screens.onboarding.adapter.Continue
import com.example.wowlucky.screens.onboarding.adapter.ContinueViewPagerAdapter
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class ContinueFragment : Fragment() {
    private lateinit var binding: FragmentContinueBinding
    private lateinit var adapter: ContinueViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContinueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.root.doOnApplyWindowInsets{view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                bottom = rect.bottom + insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            )
            insets
        }
        binding.btnContinue.setOnClickListener {
            val action = ContinueFragmentDirections.actionContinueFragmentToRegistrationInactiveFragment()
            navController.navigate(action)
        }
        binding.viewPager.offscreenPageLimit = 2
        binding.viewPager.clipToPadding = false
        binding.viewPager.setPageTransformer(CustomPageTransformer(binding.linearLayoutVP, binding.indicator, requireContext()))
        adapter = ContinueViewPagerAdapter()
        binding.viewPager.adapter = adapter
        val listOfPagers = listOf(
            Continue(R.drawable.page1_bg, R.drawable.avtomat6),
            Continue(R.drawable.page2_bg, R.drawable.avtomat9)
        )
        adapter.submitList(listOfPagers)
        binding.indicator.attachTo(binding.viewPager)
    }

}