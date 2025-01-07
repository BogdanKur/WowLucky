package com.example.wowlucky.screens.support

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentFaqBinding
import com.example.wowlucky.screens.support.adapter.FaqAdapter
import com.example.wowlucky.screens.support.adapter.FaqItem
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets


class FaqFragment : Fragment() {
    private lateinit var binding: FragmentFaqBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFaqBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FaqAdapter()
        binding.rvFaq.adapter = adapter
        val listOfQuestion = listOf(
            FaqItem(), FaqItem(), FaqItem(), FaqItem(), FaqItem(), FaqItem()
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