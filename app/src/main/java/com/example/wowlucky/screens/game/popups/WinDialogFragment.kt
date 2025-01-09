package com.example.wowlucky.screens.game.popups

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentWinDialogBinding
import com.example.wowlucky.screens.game.MainFragment
import com.example.wowlucky.screens.utils.BlurUtils.sendBlurAction

class WinDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentWinDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWinDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().sendBlurAction(MainFragment.BLUR_APPLY)
        val window = dialog?.window
        if (window != null) {
            val lp = WindowManager.LayoutParams().apply {
                copyFrom(window.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
            }
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.attributes = lp
        }
        binding.imageView2.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnGetStarted.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnNextRound.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireContext().sendBlurAction(MainFragment.BLUR_REMOVE)
    }
}