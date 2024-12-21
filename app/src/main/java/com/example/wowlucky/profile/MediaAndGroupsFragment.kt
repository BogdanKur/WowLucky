package com.example.wowlucky.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentMediaAndGroupsBinding

class MediaAndGroupsFragment : Fragment() {
    private var _binding: FragmentMediaAndGroupsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_media_and_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMediaAndGroupsBinding.bind(view)
        val navController = findNavController()
        binding.imageView.setOnClickListener {
            val action = MediaAndGroupsFragmentDirections.actionMediaFragmentToProfileFragment()
            navController.navigate(action)
        }
    }

}