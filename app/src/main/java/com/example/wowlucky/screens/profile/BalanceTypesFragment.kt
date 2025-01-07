package com.example.wowlucky.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentBalanceTypesBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BalanceTypesFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBalanceTypesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBalanceTypesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener {
            val action = BalanceTypesFragmentDirections.actionBalanceTypeFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }
}