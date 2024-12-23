package com.example.wowlucky.game

import android.graphics.RenderEffect
import android.graphics.Shader
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.wowlucky.R
import com.example.wowlucky.databinding.FragmentGameBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGameBinding.bind(view)
        val navController = findNavController()
        binding.imageView.setOnClickListener {
            val action = GameFragmentDirections.actionGameFragmentToGamePageFragment()
            navController.navigate(action)
        }
        binding.frame1.setOnClickListener {
            select(binding.frame1, binding.frame2, binding.frame3, binding.frame4, binding.frame5, binding.frame6, binding.frame7, binding.frame8)
        }
        binding.frame2.setOnClickListener {
            select(binding.frame2, binding.frame1, binding.frame3, binding.frame4, binding.frame5, binding.frame6, binding.frame7, binding.frame8)
        }
        binding.frame3.setOnClickListener {
            select(binding.frame3, binding.frame2, binding.frame1, binding.frame4, binding.frame5, binding.frame6, binding.frame7, binding.frame8)
        }
        binding.frame4.setOnClickListener {
            select(binding.frame4, binding.frame2, binding.frame3, binding.frame1, binding.frame5, binding.frame6, binding.frame7, binding.frame8)
        }
        binding.frame5.setOnClickListener {
            select(binding.frame5, binding.frame2, binding.frame3, binding.frame4, binding.frame1, binding.frame6, binding.frame7, binding.frame8)
        }
        binding.frame6.setOnClickListener {
            select(binding.frame6, binding.frame1, binding.frame3, binding.frame4, binding.frame5, binding.frame2, binding.frame7, binding.frame8)
        }
        binding.frame7.setOnClickListener {
            select(binding.frame7, binding.frame2, binding.frame1, binding.frame4, binding.frame5, binding.frame6, binding.frame3, binding.frame8)
        }
        binding.frame8.setOnClickListener {
            select(binding.frame8, binding.frame2, binding.frame3, binding.frame1, binding.frame5, binding.frame6, binding.frame7, binding.frame4)
        }
        binding.frame3.setOnClickListener {
            blurGun()
            showPopupOops()
        }
        binding.ivLive.setOnClickListener {
            blurGun()
            binding.tvPlayer1.visibility = View.VISIBLE
            binding.tvPlayer2.visibility = View.VISIBLE
            binding.tvPlayer3.visibility = View.VISIBLE
            binding.tvPlayer4.visibility = View.VISIBLE
            binding.tvPlayer5.visibility = View.VISIBLE
            binding.tvPlayer6.visibility = View.VISIBLE
            binding.tvPlayer7.visibility = View.VISIBLE
            binding.tvPlayer8.visibility = View.VISIBLE
            //Handler(Looper.getMainLooper()).postDelayed({
        // showPopupWin()
            //}, 3000)


//            Handler(Looper.getMainLooper()).postDelayed({
//                binding.allViews.setRenderEffect(
//                    RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
//                )
//                val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//                bottomNavigationView.setRenderEffect(
//                    RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
//                )
//                showPopupLose()
//            }, 3000)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun blurGun() {
        binding.frame1.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame2.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame3.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame4.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame5.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame6.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame7.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
        binding.frame8.setRenderEffect(
            RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
        )
    }

    private fun showPopupWin() {
        val popupYouWin: ConstraintLayout = requireActivity().findViewById(R.id.popupYouWin)
        popupYouWin.visibility = View.VISIBLE
        val btn: ImageView = requireActivity().findViewById(R.id.imageView2)
        btn.setOnClickListener { hidePopupWin() }
        val btn1: MaterialButton = requireActivity().findViewById(R.id.btnGetStarted)
        btn1.setOnClickListener { hidePopupWin() }
    }

    private fun hidePopupWin() {
        val popupYouWin: ConstraintLayout = requireActivity().findViewById(R.id.popupYouWin)
        popupYouWin.visibility = View.GONE
    }

    private fun showPopupLose() {
        val popupYouWin: ConstraintLayout = requireActivity().findViewById(R.id.popupYouLose)
        popupYouWin.visibility = View.VISIBLE
        val btn: ImageView = requireActivity().findViewById(R.id.imageView3)
        btn.setOnClickListener { hidePopupLose() }
        val btn1: MaterialButton = requireActivity().findViewById(R.id.btnGetStartedL)
        btn1.setOnClickListener { hidePopupLose() }
    }

    private fun hidePopupLose() {
        val popupYouWin: ConstraintLayout = requireActivity().findViewById(R.id.popupYouLose)
        popupYouWin.visibility = View.GONE
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun showPopupOops() {
        val popupYouWin: FrameLayout = requireActivity().findViewById(R.id.frameLayoutOops)
        popupYouWin.visibility = View.VISIBLE
        val btn: ImageView = requireActivity().findViewById(R.id.btnClose)
        btn.setOnClickListener {
            binding.frame1.setRenderEffect(null)
            binding.frame2.setRenderEffect(null)
            binding.frame3.setRenderEffect(null)
            binding.frame4.setRenderEffect(null)
            binding.frame5.setRenderEffect(null)
            binding.frame6.setRenderEffect(null)
            binding.frame7.setRenderEffect(null)
            binding.frame8.setRenderEffect(null)
            hidePopupOops()
        }
        val btn1: MaterialButton = requireActivity().findViewById(R.id.btnOk1)
        btn1.setOnClickListener {
            binding.frame1.setRenderEffect(null)
            binding.frame2.setRenderEffect(null)
            binding.frame3.setRenderEffect(null)
            binding.frame4.setRenderEffect(null)
            binding.frame5.setRenderEffect(null)
            binding.frame6.setRenderEffect(null)
            binding.frame7.setRenderEffect(null)
            binding.frame8.setRenderEffect(null)
            hidePopupOops()
        }
    }

    private fun hidePopupOops() {

        val popupYouWin: FrameLayout = requireActivity().findViewById(R.id.frameLayoutOops)
        popupYouWin.visibility = View.GONE
    }

    fun select(frame1: FrameLayout, frame2: FrameLayout, frame3: FrameLayout, frame4: FrameLayout, frame5: FrameLayout, frame6: FrameLayout, frame7: FrameLayout, frame8: FrameLayout) {
        frame1.setBackgroundResource(R.drawable.select_game)
        frame2.setBackgroundResource(R.drawable.game)
        frame3.setBackgroundResource(R.drawable.game)
        frame4.setBackgroundResource(R.drawable.game)
        frame5.setBackgroundResource(R.drawable.game)
        frame6.setBackgroundResource(R.drawable.game)
        frame7.setBackgroundResource(R.drawable.game)
        frame8.setBackgroundResource(R.drawable.game)
    }
}