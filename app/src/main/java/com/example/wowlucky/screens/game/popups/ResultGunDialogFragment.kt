package com.example.wowlucky.screens.game.popups

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentResultGunDialogBinding
import com.example.wowlucky.screens.game.MainFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ResultGunDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentResultGunDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result_gun_dialog, container, false)
    }

    private fun sendBlurAction(type: String) {
        val intent = Intent(MainFragment.ACTION_BLUR).apply {
            putExtra(MainFragment.EXTRA_BLUR_TYPE, type)
        }
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResultGunDialogBinding.bind(view)
        sendBlurAction(MainFragment.BLUR_APPLY)
        binding.btnTakePicture9.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sendBlurAction(MainFragment.BLUR_REMOVE)
    }
}