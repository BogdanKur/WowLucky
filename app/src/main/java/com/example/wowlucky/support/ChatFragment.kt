package com.example.wowlucky.support

import android.graphics.Rect
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.updatePadding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.BlurUtils.applyBlur
import com.example.wowlucky.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentChatBinding
import com.example.wowlucky.doOnApplyWindowInsets
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)
        val navController = findNavController()
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
            applyBlur(requireContext(), binding.rvChats)
            applyBlur(requireContext(), binding.llNoMessage)
            binding.linearLayoutSelect.visibility = View.VISIBLE
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