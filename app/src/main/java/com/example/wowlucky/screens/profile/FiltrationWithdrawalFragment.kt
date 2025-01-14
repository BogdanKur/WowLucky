package com.example.wowlucky.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentFiltrationWithdrawalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FiltrationWithdrawalFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFiltrationWithdrawalBinding
    private var isImageBackgroundChanged = false
    private var isImageBackgroundChangedWin = false
    private var isImageBackgroundChangedLose = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFiltrationWithdrawalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChooseDate.setOnClickListener {
            if (isImageBackgroundChanged) {
                binding.imageView11.setBackgroundResource(R.drawable.choose_date)
                binding.btnChooseDate.setTextColor(resources.getColor(R.color.white))
                binding.btnChooseDate.text = "Choose date"
            } else {
                binding.imageView11.setBackgroundResource(R.drawable.choose_date_bg1)
                binding.btnChooseDate.text = "01.05.2022-01.06.2023"
                binding.btnChooseDate.setTextColor(resources.getColor(R.color.black))
            }
            isImageBackgroundChanged = !isImageBackgroundChanged
        }
        binding.btnWin.setOnClickListener {
            if (isImageBackgroundChangedWin) {
                binding.imageView12.setBackgroundResource(R.drawable.component_23)
                binding.btnChooseDate.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.imageView12.setBackgroundResource(R.drawable.choose_win_lose)
                binding.btnWin.setTextColor(resources.getColor(R.color.black))
            }
            isImageBackgroundChangedWin = !isImageBackgroundChangedWin
        }

        binding.btnLose.setOnClickListener {
            if (isImageBackgroundChangedLose) {
                binding.imageView13.setBackgroundResource(R.drawable.component_23)
            } else {
                binding.imageView13.setBackgroundResource(R.drawable.choose_win_lose)
                binding.btnLose.setTextColor(resources.getColor(R.color.black))
            }
            isImageBackgroundChangedLose = !isImageBackgroundChangedLose
        }
        binding.imageView.setOnClickListener {
            val action = FiltrationWithdrawalFragmentDirections.actionFiltrationFragmentToTransactionsFragment()
            findNavController().navigate(action)
        }
        binding.btnShowResults.setOnClickListener {
            val action = FiltrationWithdrawalFragmentDirections.actionFiltrationFragmentToTransactionsFragment()
            findNavController().navigate(action)
        }
    }


}