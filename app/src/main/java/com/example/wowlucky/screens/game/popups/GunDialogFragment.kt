package com.example.wowlucky.screens.game.popups

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGunDialogBinding
import com.example.wowlucky.screens.game.MainFragment
import com.example.wowlucky.screens.utils.BlurUtils.sendBlurAction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class GunDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentGunDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGunDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().sendBlurAction(MainFragment.BLUR_APPLY)
        binding.btnTakePicture1.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().sendBlurAction(MainFragment.BLUR_REMOVE)
    }
}