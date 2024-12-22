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
import androidx.core.view.marginBottom
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentChatBinding
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

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentChatBinding.bind(view)
        val navController = findNavController()
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.visibility = View.GONE
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
            val action = ChatFragmentDirections.actionChatFragmentToSupportFragment()
            navController.navigate(action)
        }
        binding.btnPlus.setOnClickListener {
            binding.rvChats.setRenderEffect(RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP))
            binding.llNoMessage.setRenderEffect(RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP))
            binding.linearLayoutSelect.visibility = View.VISIBLE
        }
        binding.llImage.setOnClickListener {
            binding.rvChats.setRenderEffect(null)
            binding.llNoMessage.setRenderEffect(null)
            binding.linearLayoutSelect.visibility = View.GONE
        }
        binding.llFile.setOnClickListener {
            binding.rvChats.setRenderEffect(null)
            binding.llNoMessage.setRenderEffect(null)
            binding.linearLayoutSelect.visibility = View.GONE
        }
        val rootView = requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > 200) {
                val params = binding.linearLayout2.layoutParams as ViewGroup.MarginLayoutParams
                params.bottomMargin = keypadHeight-10
                binding.linearLayout2.layoutParams = params
            } else {
                val params = binding.linearLayout2.layoutParams as ViewGroup.MarginLayoutParams
                params.bottomMargin = 0
                binding.linearLayout2.layoutParams = params
            }
        }
    }
}