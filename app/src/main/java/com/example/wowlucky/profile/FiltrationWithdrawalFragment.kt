package com.example.wowlucky.profile

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentFiltrationWithdrawalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class FiltrationWithdrawalFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFiltrationWithdrawalBinding? = null
    private val binding get() = _binding!!
    private var isImageBackgroundChanged = false
    private var isImageBackgroundChangedWin = false
    private var isImageBackgroundChangedLose = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filtration_withdrawal, container, false)
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFiltrationWithdrawalBinding.bind(view)
        val navController = findNavController()
        binding.btnChooseDate.setOnClickListener {
            if (isImageBackgroundChanged) {
                binding.imageView11.setBackgroundResource(R.drawable.choose_date)
                binding.btnChooseDate.setTextColor(R.color.white)
                binding.btnChooseDate.text = "Choose date"
            } else {
                binding.imageView11.setBackgroundResource(R.drawable.choose_date_bg1)
                binding.btnChooseDate.text = "01.05.2022-01.06.2023"
                binding.btnChooseDate.setTextColor(R.color.black)
            }
            isImageBackgroundChanged = !isImageBackgroundChanged
        }
        binding.btnWin.setOnClickListener {
            if (isImageBackgroundChangedWin) {
                binding.imageView12.setBackgroundResource(R.drawable.component_23)
                binding.btnChooseDate.setTextColor(R.color.white)
            } else {
                binding.imageView12.setBackgroundResource(R.drawable.choose_win_lose)
                binding.btnWin.setTextColor(R.color.black)
            }
            isImageBackgroundChangedWin = !isImageBackgroundChangedWin
        }

        binding.btnLose.setOnClickListener {
            if (isImageBackgroundChangedLose) {
                binding.imageView13.setBackgroundResource(R.drawable.component_23)
            } else {
                binding.imageView13.setBackgroundResource(R.drawable.choose_win_lose)
                binding.btnLose.setTextColor(R.color.black)
            }
            isImageBackgroundChangedLose = !isImageBackgroundChangedLose
        }
        binding.imageView.setOnClickListener {
            val root = requireActivity().findViewById<ConstraintLayout>(R.id.rootLayout)
            root.setRenderEffect(null)
            dismiss()
        }
        binding.btnShowResults.setOnClickListener {
            val root = requireActivity().findViewById<ConstraintLayout>(R.id.rootLayout)
            root.setRenderEffect(null)
            dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onDestroy() {
        super.onDestroy()
        dismiss()
        val root = requireActivity().findViewById<ConstraintLayout>(R.id.rootLayout)
        root.setRenderEffect(null)
    }


}