package com.example.wowlucky.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.Continue
import com.example.wowlucky.ContinueViewPagerAdapter
import com.example.wowlucky.CustomPageTransformer
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueBinding
import com.example.wowlucky.doOnApplyWindowInsets

class ContinueFragment : Fragment() {
    private var _binding: FragmentContinueBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ContinueViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_continue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContinueBinding.bind(view)
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
        val listOfPagers = listOf(Continue(R.drawable.page1_bg, R.drawable.avtomat6),
            Continue(R.drawable.page2_bg, R.drawable.avtomat9)
        )
        adapter.submitList(listOfPagers)
        binding.indicator.attachTo(binding.viewPager)
    }

}