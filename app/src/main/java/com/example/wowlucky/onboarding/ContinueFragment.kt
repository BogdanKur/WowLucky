package com.example.wowlucky.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.Continue
import com.example.wowlucky.ContinueViewPagerAdapter
import com.example.wowlucky.ContinueViewPagerAdapterClickItem
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueBinding

class ContinueFragment : Fragment(), ContinueViewPagerAdapterClickItem {
    private var _binding: FragmentContinueBinding? = null
    private val binding get() = _binding!!

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
        val adapter = ContinueViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
        val listOfPagers = listOf(Continue(R.drawable.frame_2085660594, R.drawable.avtomat5, "MONTHLY GAMES!", "Join us in monthly gaming events that will keep you on the edge of your seat! Compete with other gamers. "),
            Continue(R.drawable.frame_2085660596, R.drawable.avtomat6, "TAKE & BALLS, GET 1 LIFE!", " Each time you successfully collect 8 artworks, you earn a life, unlocking new opportunities in the game. ")
        )
        adapter.submitList(listOfPagers)
    }

    override fun onButtonClick() {
        val action = ContinueFragmentDirections.actionContinueFragmentToRegistrationInactiveFragment()
        findNavController().navigate(action)
    }

    override fun onSkipClick() {
    }

}