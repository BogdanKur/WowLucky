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
    var isOpen6 = false

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
        binding.imageView.setOnClickListener {
            val action = FaqFragmentDirections.actionFaqFragmentToSupportFragment()
            navController.navigate(action)
        }
        binding.openQuestionOne.setOnClickListener {
            isOpen1 = openQuestionAnswer(binding.openQuestionOne, binding.questionAnswer1, isOpen1, binding.clQuestionOne)
        }
        binding.openQuestionTwo.setOnClickListener {
            isOpen2 = openQuestionAnswer(binding.openQuestionTwo, binding.questionAnswer2, isOpen2, binding.clQuestionTwo)
        }
        binding.openQuestionThree.setOnClickListener {
            isOpen3 = openQuestionAnswer(binding.openQuestionThree, binding.questionAnswer3, isOpen3, binding.clQuestionThree)
        }
        binding.openQuestionFour.setOnClickListener {
            isOpen4 = openQuestionAnswer(binding.openQuestionFour, binding.questionAnswer4, isOpen4, binding.clQuestionFour)
        }
        binding.openQuestionFive.setOnClickListener {
            isOpen5 = openQuestionAnswer(binding.openQuestionFive, binding.questionAnswer5, isOpen5, binding.clQuestionFive)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun openQuestionAnswer(button: ImageView, open: LinearLayout, isOpen: Boolean, clQuestion: ConstraintLayout): Boolean {
        val caretDownDrawable = resources.getDrawable(R.drawable.up_white, null)
        val caretUpDrawable = resources.getDrawable(R.drawable.down_white, null)
        if (!isOpen) {
            clQuestion.setBackgroundResource(R.drawable.faq_bg1)
            open.visibility = View.VISIBLE
            button.setImageDrawable(caretUpDrawable)
            return true
        } else {
            clQuestion.setBackgroundResource(R.drawable.faq_bg)
            open.visibility = View.GONE
            button.setImageDrawable(caretDownDrawable)
            return false
        }
    }

}