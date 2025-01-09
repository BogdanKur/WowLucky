package com.example.wowlucky.screens.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.NavHostFragment
import com.example.wowlucky.R
import com.example.wowlucky.screens.utils.BlurUtils.applyBlur
import com.example.wowlucky.screens.utils.BlurUtils.removeBlur
import com.example.wowlucky.databinding.FragmentMainBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    companion object {
        const val ACTION_BLUR = "com.example.ACTION_BLUR"
        const val EXTRA_BLUR_TYPE = "BLUR_TYPE"
        const val BLUR_APPLY = "APPLY"
        const val BLUR_REMOVE = "REMOVE"
    }
    private val blurReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val blurType = intent?.getStringExtra(EXTRA_BLUR_TYPE)

            when (blurType) {
                BLUR_APPLY -> applyBlur(context!!, binding.root)
                BLUR_REMOVE -> removeBlur(binding.root)
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = (childFragmentManager.findFragmentById(R.id.nav_host_fragment1) as NavHostFragment).navController
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            blurReceiver,
            IntentFilter(ACTION_BLUR)
        )
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

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(blurReceiver)
    }
}