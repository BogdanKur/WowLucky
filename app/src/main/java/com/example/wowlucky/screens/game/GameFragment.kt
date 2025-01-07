package com.example.wowlucky.screens.game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.screens.utils.BlurUtils.removeBlur
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGameBinding
import com.example.wowlucky.screens.game.adapter.GameAdapter
import com.example.wowlucky.screens.game.adapter.GameItem
import com.example.wowlucky.screens.game.interfaces.GameItemClick
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class GameFragment : Fragment(), GameItemClick {
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeBlur(binding.root)
        val navController = findNavController()
        val gameItems = listOf(
            GameItem(imageResource = R.drawable.avtomat2),
            GameItem(imageResource = R.drawable.avtomat3),
            GameItem(imageResource = R.drawable.avtomat4),
            GameItem(imageResource = R.drawable.avtomat5),
            GameItem(imageResource = R.drawable.avtomat6),
            GameItem(imageResource = R.drawable.avtomat7),
            GameItem(imageResource = R.drawable.avtomat8),
            GameItem(imageResource = R.drawable.avtomat9),
        )

        val adapter = GameAdapter(this)
        binding.rvGames.adapter = adapter
        adapter.submitList(gameItems)
        binding.imageView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivLive.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                showPopupWin()
            }, 100)
        }
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
    }

    private fun showPopupWin() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.winDialogFragment)
    }

    private fun showPopupLose() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.loseDialogFragment)
    }

    private fun showPopupChoose() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.chooseDialogFragment)
    }
    private fun showPopupGun() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.gunDialogFragment)
    }
    private fun showPopupResultGun() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.resultGunDialogFragment)
    }
    private fun showPopupOops() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.oopsDialogFragment)
    }

    override fun onItemClick() {
        showPopupChoose()
    }

}