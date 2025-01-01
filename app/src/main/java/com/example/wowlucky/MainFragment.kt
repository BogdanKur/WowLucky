package com.example.wowlucky

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wowlucky.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        val navController = (childFragmentManager.findFragmentById(R.id.nav_host_fragment1) as NavHostFragment).navController

        binding.llHome.setOnClickListener {
            binding.llHome.setBackgroundResource(R.drawable.home_choose)
            binding.llChat.setBackgroundResource(R.drawable.chat_white)
            binding.llWallet.setBackgroundResource(R.drawable.wallet_white)
            binding.llProfile.setBackgroundResource(R.drawable.profile_white)
            navController.navigate(R.id.gamePageFragment)
        }
        binding.llChat.setOnClickListener {
            binding.llHome.setBackgroundResource(R.drawable.home_white)
            binding.llChat.setBackgroundResource(R.drawable.chat_choose)
            binding.llWallet.setBackgroundResource(R.drawable.wallet_white)
            binding.llProfile.setBackgroundResource(R.drawable.profile_white)
            navController.navigate(R.id.supportFragment)
        }
        binding.llWallet.setOnClickListener {
            binding.llHome.setBackgroundResource(R.drawable.home_white)
            binding.llChat.setBackgroundResource(R.drawable.chat_white)
            binding.llWallet.setBackgroundResource(R.drawable.wallet_choose)
            binding.llProfile.setBackgroundResource(R.drawable.profile_white)
            navController.navigate(R.id.gamingTransactionsFragment)
        }
        binding.llProfile.setOnClickListener {
            binding.llHome.setBackgroundResource(R.drawable.home_white)
            binding.llChat.setBackgroundResource(R.drawable.chat_white)
            binding.llWallet.setBackgroundResource(R.drawable.wallet_white)
            binding.llProfile.setBackgroundResource(R.drawable.profile_choose)
            navController.navigate(R.id.profileFragment)
        }
        binding.bottomNavigationView.doOnApplyWindowInsets{ view, insets, rect ->
            view.updatePadding(
                bottom = rect.bottom + insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            )
            insets
        }
    }
}