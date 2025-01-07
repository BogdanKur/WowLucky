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
    private var _binding: FragmentBalanceTypesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_balance_types, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBalanceTypesBinding.bind(view)
        binding.btnOk.setOnClickListener {
            val action = BalanceTypesFragmentDirections.actionBalanceTypeFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }
}