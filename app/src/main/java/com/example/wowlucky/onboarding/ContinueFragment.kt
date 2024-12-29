package com.example.wowlucky.onboarding

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.wowlucky.Continue
import com.example.wowlucky.ContinueViewPagerAdapter
import com.example.wowlucky.CustomPageTransformer
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueBinding

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