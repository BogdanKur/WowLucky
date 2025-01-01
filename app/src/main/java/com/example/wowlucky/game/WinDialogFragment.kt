package com.example.wowlucky.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentWinDialogBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class WinDialogFragment : DialogFragment() {
    private var _binding: FragmentWinDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_win_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWinDialogBinding.bind(view)
        val window = dialog?.window
        if (window != null) {
            val lp = WindowManager.LayoutParams().apply {
                copyFrom(window.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
            }
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.attributes = lp
        }
        val allView: ConstraintLayout = requireActivity().findViewById(R.id.allViewss)
        val bottomNav = requireActivity().findViewById<LinearLayout>(R.id.bottomNavigationView)
        binding.imageView2.setOnClickListener {
            removeBlur(allView)
            removeBlur(bottomNav)
            findNavController().popBackStack()
        }
        binding.btnGetStarted.setOnClickListener {
            removeBlur(allView)
            removeBlur(bottomNav)
            findNavController().popBackStack()
        }
        binding.btnNextRound.setOnClickListener {
            removeBlur(allView)
            removeBlur(bottomNav)
            findNavController().popBackStack()
        }
    }
}