package com.example.wowlucky.screens.game

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGamePageBinding
import com.example.wowlucky.screens.game.GamePageFragmentDirections
import com.example.wowlucky.screens.game.adapter.News
import com.example.wowlucky.screens.game.adapter.NewsAdapter
import com.example.wowlucky.screens.game.adapter.NewsViewPagerAdapterClickItem
import com.example.wowlucky.screens.utils.doOnApplyWindowInsets

class GamePageFragment : Fragment(), NewsViewPagerAdapterClickItem {
    private lateinit var binding: FragmentGamePageBinding
    private lateinit var newsAdapter: NewsAdapter
    private var remaining = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.doOnApplyWindowInsets { view, insets, rect ->
            view.updatePadding(
                top = rect.top + insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            )
            insets
        }
        val navController = findNavController()
        binding.ivCenter.setOnClickListener { view ->
            showPopupGun()
            val scaleUpX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.1f)
            val scaleUpY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.1f)
            val scaleDownX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1.1f, 1f)
            val scaleDownY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.1f, 1f)
            scaleUpX.duration = 150
            scaleUpY.duration = 150
            scaleDownX.duration = 150
            scaleDownY.duration = 150
            val animatorSet = AnimatorSet()
            animatorSet.play(scaleUpX).with(scaleUpY)
            animatorSet.play(scaleDownX).with(scaleDownY).after(scaleUpX)
            animatorSet.start()
            binding.circularProgressBar.elementToUpdateIndex = 0
            val drawableList = listOf(
                ContextCompat.getDrawable(requireContext(), R.drawable.progress_element_1)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.progress_element_2)!!)
            for (i in 0 until binding.circularProgressBar.totalElements+1) {
                binding.circularProgressBar.updateDrawableAtIndex(i, drawableList[0])
            }
            binding.circularProgressBar.elementToUpdateIndex = 0
            startTimer()

        }
        newsAdapter = NewsAdapter(this)
        val newsList = listOf(
            News("News", "Lorem ipsum dolor sit amet consectetur.Lorem ipsum dolor sit amet."),
            News("Заголовок 2", "Описание 2"),
            News("Заголовок 3", "Описание 3")
        )

        newsAdapter.submitList(newsList)
        binding.viewPager.adapter = newsAdapter

        binding.ivBell.setOnClickListener {
            val action = GamePageFragmentDirections.actionGamePageFragmentToNotificationFragment()
            navController.navigate(action)
        }

    }

    private fun startTimer() {
        when(remaining) {
            1 -> {
                binding.ivCenter.setBackgroundResource(R.drawable.center_icon11)
            }
            2 -> {
                binding.ivCenter.setBackgroundResource(R.drawable.center_vnesh1)
            }
        }
        val totalDuration = 10 * 60 * 10L
        val interval = totalDuration / 89
        val drawableList = listOf(
            ContextCompat.getDrawable(requireContext(), R.drawable.progress_element_1)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.progress_element_2)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.progress_element_3)!!
        )

        val timer = object : CountDownTimer(totalDuration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                if (binding.circularProgressBar.elementToUpdateIndex <= binding.circularProgressBar.totalElements) {
                    val newDrawable = drawableList[1]
                    binding.circularProgressBar.updateDrawableAtIndex(
                        binding.circularProgressBar.elementToUpdateIndex,
                        newDrawable
                    )
                    binding.circularProgressBar.elementToUpdateIndex++

                    val progress = binding.circularProgressBar.elementToUpdateIndex.toFloat() / binding.circularProgressBar.totalElements * 100
                    binding.circularProgressBar.setOuterProgress(progress)
                }
            }

            override fun onFinish() {
                when(remaining) {
                    1 -> {
                        binding.ivCenter.setBackgroundResource(R.drawable.center_all1)
                    }
                    2 -> {
                        binding.ivCenter.setBackgroundResource(R.drawable.center_icon11)
                    }
                    3 -> {
                        binding.ivCenter.setBackgroundResource(R.drawable.center_vnesh1)
                    }
                }
                binding.circularProgressBar.elementToUpdateIndex = 0
                for (i in 0 until binding.circularProgressBar.totalElements+1) {
                    binding.circularProgressBar.updateDrawableAtIndex(i, drawableList[2])
                }
                binding.circularProgressBar.elementToUpdateIndex = 0
                remaining--
                binding.circularProgressBar.setOuterProgress(100f)
            }
        }.start()
    }

    private fun showPopupGun() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.gunDialogFragment)
    }
    private fun showPopupResultGun() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.resultGunDialogFragment)
    }


    override fun onButtonClick() {
        val action = GamePageFragmentDirections.actionGamePageFragmentToGameFragment()
        findNavController().navigate(action)
    }
}