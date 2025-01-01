package com.example.wowlucky.support

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentFaqBinding
import com.example.wowlucky.doOnApplyWindowInsets
import com.google.android.material.bottomnavigation.BottomNavigationView


class FaqFragment : Fragment() {
    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFaqBinding.bind(view)
        val adapter = FaqAdapter()
        binding.rvFaq.adapter = adapter
        val listOfQuestion = listOf(
            FaqItem(),FaqItem(), FaqItem(), FaqItem(), FaqItem(), FaqItem()
        )
        adapter.submitList(listOfQuestion)
        ViewCompat.setOnApplyWindowInsetsListener(binding.rvFaq) { v, insets ->
            val bottomInset = insets.systemGestureInsets.bottom
            val paddingBottom = resources.getDimension(R.dimen._36dp).toInt()
            v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, bottomInset + paddingBottom)
            insets
        }
        binding.imageView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }

    }

}