package com.example.wowlucky.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.wowlucky.Continue
import com.example.wowlucky.ContinueViewPagerAdapter
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentContinueBinding

class ContinueFragment : Fragment() {
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
        binding.btnContinue.setOnClickListener {
            val action = ContinueFragmentDirections.actionContinueFragmentToRegistrationInactiveFragment()
            navController.navigate(action)
        }
        val adapter = ContinueViewPagerAdapter()
        binding.viewPager.adapter = adapter
        val listOfPagers = listOf(Continue(R.drawable.frame_2085660594, R.drawable.avtomat5),
            Continue(R.drawable.frame_2085660596, R.drawable.avtomat6)
        )
        adapter.submitList(listOfPagers)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.tvMonthlyGames.text = "MONTHLY GAMES!"
                        binding.tvJoin.text = "Join us in monthly gaming events that will keep you on the edge of your seat! Compete with other gamers. "
                    }
                    1 -> {
                        binding.tvMonthlyGames.text = "TAKE & BALLS, GET 1 LIFE!"
                        binding.tvJoin.text = "Each time you successfully collect 8 artworks, you earn a life, unlocking new opportunities in the game. "
                    }
                }
            }
        })
    }
}