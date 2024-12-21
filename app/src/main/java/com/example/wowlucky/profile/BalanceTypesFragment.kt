package com.example.wowlucky.profile

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBalanceTypesBinding.bind(view)
        binding.btnOk.setOnClickListener {
            val root = requireActivity().findViewById<ScrollView>(R.id.scrollViewRoot)
            root.setRenderEffect(null)
            dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onDestroy() {
        super.onDestroy()
        val root = requireActivity().findViewById<ScrollView>(R.id.scrollViewRoot)
        root.setRenderEffect(null)
        dismiss()
    }
}