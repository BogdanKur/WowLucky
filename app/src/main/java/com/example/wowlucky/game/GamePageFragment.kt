package com.example.wowlucky.game

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGamePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class GamePageFragment : Fragment(), NewsViewPagerAdapterClickItem {
    private var _binding: FragmentGamePageBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_page, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGamePageBinding.bind(view)
        val navController = findNavController()
        binding.ivCenter.setOnClickListener { view ->
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
            //showPopupFirstGun()

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

    @RequiresApi(Build.VERSION_CODES.S)
    private fun showPopupFirstGun() {
        val popupYouWin: ConstraintLayout = requireActivity().findViewById(R.id.popupYouFirstGun)
        popupYouWin.visibility = View.VISIBLE
        val btn: MaterialButton = requireActivity().findViewById(R.id.btnTakePicture1)
        btn.setOnClickListener { hidePopupFirstGun() }
        binding.allViews.setRenderEffect(RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP))
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setRenderEffect(RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP))
    }
    @RequiresApi(Build.VERSION_CODES.S)
    private fun hidePopupFirstGun() {
        val popupYouWin: ConstraintLayout = requireActivity().findViewById(R.id.popupYouFirstGun)
        popupYouWin.visibility = View.GONE
        binding.allViews.setRenderEffect(null)
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setRenderEffect(null)
    }

    override fun onButtonClick() {
        val action = GamePageFragmentDirections.actionGamePageFragmentToGameFragment()
        findNavController().navigate(action)
    }
}