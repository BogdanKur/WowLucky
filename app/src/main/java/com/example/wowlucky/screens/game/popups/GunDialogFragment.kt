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

    private fun sendBlurAction(type: String) {
        val intent = Intent(MainFragment.ACTION_BLUR).apply {
            putExtra(MainFragment.EXTRA_BLUR_TYPE, type)
        }
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendBlurAction(MainFragment.BLUR_APPLY)
        binding.btnTakePicture1.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sendBlurAction(MainFragment.BLUR_REMOVE)
    }
}