package com.example.wowlucky.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentChooseDialogBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChooseDialogFragment : DialogFragment() {
    private var _binding: FragmentChooseDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChooseDialogBinding.bind(view)
        val allView: ConstraintLayout = requireActivity().findViewById(R.id.allViewss)
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        binding.imageView92.setOnClickListener {
            removeBlur(allView)
            removeBlur(bottomNav)
            findNavController().popBackStack()
        }
        binding.btnNextRound92.setOnClickListener {
            removeBlur(allView)
            removeBlur(bottomNav)
            findNavController().popBackStack()
        }
    }
}