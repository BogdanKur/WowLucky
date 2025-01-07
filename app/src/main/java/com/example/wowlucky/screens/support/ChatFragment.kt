package com.example.wowlucky.screens.support

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.utils.BlurUtils.applyBlur
import com.example.wowlucky.screens.utils.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentChatBinding
import com.example.wowlucky.screens.support.adapter.MessageAdapter
import com.example.wowlucky.screens.support.adapter.Messages
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private var isClick = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MessageAdapter()
        binding.rvChats.adapter = adapter
        val messages = listOf(
            Messages.DateMessage("17.11.2024"),
            Messages.BotMessage("Hello, how can I help you?"),
            Messages.YourMessage("I need some help with my account."),
            Messages.BotMessage("Sure, I'd be happy to help!")
        )

        adapter.submitList(messages)
        binding.imageView.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnPlus.setOnClickListener {
            if(isClick) {
                isClick = false
                removeBlur(binding.rvChats)
                removeBlur(binding.llNoMessage)
                binding.linearLayoutSelect.visibility = View.GONE
            } else {
                isClick = true
                applyBlur(requireContext(), binding.rvChats)
                applyBlur(requireContext(), binding.llNoMessage)
                binding.linearLayoutSelect.visibility = View.VISIBLE
            }

        }
        binding.llImage.setOnClickListener {
            removeBlur(binding.rvChats)
            removeBlur(binding.llNoMessage)
            binding.linearLayoutSelect.visibility = View.GONE
        }
        binding.llFile.setOnClickListener {
            removeBlur(binding.rvChats)
            removeBlur(binding.llNoMessage)
            binding.linearLayoutSelect.visibility = View.GONE
        }

        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                bottom = rect.bottom + if (insets.getInsets(WindowInsetsCompat.Type.ime()).bottom == 0) {
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
                } else {
                    insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                },
            )
            insets
        }
    }
}