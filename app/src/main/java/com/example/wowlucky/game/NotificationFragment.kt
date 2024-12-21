package com.example.wowlucky.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNotificationBinding.bind(view)
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
    }
}