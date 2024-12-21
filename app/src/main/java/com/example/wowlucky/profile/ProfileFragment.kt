package com.example.wowlucky.profile

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var isImageBackgroundChanged = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        val navController = findNavController()
        binding.cl2.setOnClickListener {
            binding.scrollViewRoot.setRenderEffect(RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP))
            val bottomSheet = BalanceTypesFragment()
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "BalanceTypesFragment") }
            bottomSheet.dialog?.setOnDismissListener {
                binding.scrollViewRoot.setRenderEffect(null)
            }
        }
        binding.btnReferral.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToReferralFragment()
            navController.navigate(action)
        }
        binding.btnNotifications.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToNotificationSettingsFragment()
            navController.navigate(action)
        }
        binding.btnGaming.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToGamingFragment()
            navController.navigate(action)
        }
        binding.btnMedia.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToMediaFragment()
            navController.navigate(action)
        }
        binding.btnWithdrawals.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToWithdrawalsFragment()
            navController.navigate(action)
        }
        binding.btnGame.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToGameFragment()
            navController.navigate(action)
        }
        binding.btnNews.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToGameFragment()
            navController.navigate(action)
        }
        binding.btnSupport.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToSupportFragment()
            navController.navigate(action)
        }
        binding.btnFaq.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToFaqFragment()
            navController.navigate(action)
        }
        binding.btnEarning.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToMyProfileFragment()
            navController.navigate(action)
        }
        binding.btnLanguage.setOnClickListener {
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