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
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentFaqBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class FaqFragment : Fragment() {
    private var _binding: FragmentFaqBinding? = null
    private val binding get() = _binding!!
    var isOpen1 = false
    var isOpen2 = false
    var isOpen3 = false
    var isOpen4 = false
    var isOpen5 = false
    var lastPos = "null"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_faq, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFaqBinding.bind(view)
        val navController = findNavController()
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.GONE
        arguments?.let { bundle->
            lastPos = bundle.getString("support").toString()
        }
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
            if(lastPos == "support") {
                val action = FaqFragmentDirections.actionFaqFragmentToSupportFragment()
                navController.navigate(action)
            } else {
                val action = FaqFragmentDirections.actionFaqFragmentToGamePageFragment()
                navController.navigate(action)
            }
        }

    }

    fun openQuestionAnswer(button: ImageView, open: LinearLayout, isOpen: Boolean, clQuestion: ConstraintLayout): Boolean {
        if (!isOpen) {
            clQuestion.setBackgroundResource(R.drawable.faq_bg1)
            open.visibility = View.VISIBLE
            button.setImageResource(R.drawable.down_white)
            return true
        } else {
            clQuestion.setBackgroundResource(R.drawable.faq_bg)
            open.visibility = View.GONE
            button.setImageResource(R.drawable.up_white)
            return false
        }
    }

}