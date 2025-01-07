package com.example.wowlucky.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentNotificationBinding
import com.example.wowlucky.screens.game.adapter.Message
import com.example.wowlucky.screens.game.adapter.NotificationAdapter
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val adapter = NotificationAdapter()
        binding.rvNotification.adapter = adapter
        val listOfNotification = listOf(
            Message.DateMessage("17.11.2024"),
            Message.WinMessage("230"),
            Message.LifeMessage("12"),
            Message.DateMessage("17.11.2024"),
            Message.WithdrawMessage("200")
        )
        adapter.submitList(listOfNotification)
        binding.imageView.setOnClickListener {
            val action = NotificationFragmentDirections.actionNotificationFragmentToGamePageFragment()
            navController.navigate(action)
        }
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
    }
}