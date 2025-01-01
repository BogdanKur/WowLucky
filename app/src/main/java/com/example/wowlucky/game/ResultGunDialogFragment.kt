package com.example.wowlucky.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGunDialogBinding
import com.example.wowlucky.databinding.FragmentResultGunDialogBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResultGunDialogBinding.bind(view)
        val allView: ConstraintLayout = requireActivity().findViewById(R.id.alls)
        val bottomNav = requireActivity().findViewById<LinearLayout>(R.id.bottomNavigationView)
        binding.btnTakePicture9.setOnClickListener {
            removeBlur(allView)
            removeBlur(bottomNav)
            findNavController().popBackStack()
        }
    }
}