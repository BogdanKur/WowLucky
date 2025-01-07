package com.example.wowlucky.screens.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.utils.BlurUtils.applyBlur
import com.example.wowlucky.screens.utils.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentProfileBinding
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var isImageBackgroundChanged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeBlur(binding.root)
        val navController = findNavController()
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
        binding.cl2.setOnClickListener {
            applyBlur(requireContext(), binding.scrollViewRoot)
            val action = ProfileFragmentDirections.actionProfileFragmentToBalanceTypeFragment()
            navController.navigate(action)
        }
        binding.referral.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToReferralFragment()
            navController.navigate(action)
        }
        binding.notifications.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToNotificationSettingsFragment()
            navController.navigate(action)
        }
        binding.gaming.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToGamingFragment()
            navController.navigate(action)
        }
        binding.media.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToMediaFragment()
            navController.navigate(action)
        }
        binding.withdrawals.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToWithdrawalsFragment()
            navController.navigate(action)
        }
        binding.game.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToGameFragment()
            navController.navigate(action)
        }
        binding.news.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToGameFragment()
            navController.navigate(action)
        }
        binding.support.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToSupportFragment()
            navController.navigate(action)
        }
        binding.faq.setOnClickListener {
            val navControllers = requireActivity().findNavController(R.id.nav_host_fragment)
            navControllers.navigate(R.id.faqFragment)
        }
        binding.earnings.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToMyProfileFragment()
            navController.navigate(action)
        }
        binding.language.setOnClickListener {
            if (isImageBackgroundChanged) {
                binding.tvRu.visibility = View.GONE
                binding.tvEs.visibility = View.GONE
            } else {
                binding.tvRu.visibility = View.VISIBLE
                binding.tvEs.visibility = View.VISIBLE
            }
            isImageBackgroundChanged = !isImageBackgroundChanged
        }
    }
}