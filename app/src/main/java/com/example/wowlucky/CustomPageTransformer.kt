package com.example.wowlucky

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.internal.ViewUtils.dpToPx
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class CustomPageTransformer(private val linearLayout: LinearLayout, private var indicator: DotsIndicator, private val context: Context) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val density = context.resources.displayMetrics.density

        val radius = 10 * density

        val angle = position * 35
        val radians = Math.toRadians(angle.toDouble())
        val translationX = (radius * sin(radians)).toFloat()
        val translationY = (radius * (1 - cos(radians))).toFloat()

        page.translationX = translationX
        page.translationY = translationY
        page.rotation = angle

        if (position < -1) {
            linearLayout.visibility = View.GONE
        } else if (position <= 1) {
            linearLayout.visibility = View.VISIBLE

            when (position) {
                -1f -> {
                    //indicator.setImageResource(R.drawable.second_view_pager)
                    linearLayout.findViewById<TextView>(R.id.tvMonthlyGames).text = "TAKE & BALLS, GET 1 LIFE!"
                    linearLayout.findViewById<TextView>(R.id.tvJoin).text = "Each time you successfully collect 8 artworks, you earn a life, unlocking new opportunities in the game."
                }

                1f -> {
                    //indicator.setImageResource(R.drawable.frame_2085660591)
                    linearLayout.findViewById<TextView>(R.id.tvMonthlyGames).text = "MONTHLY GAMES!"
                    linearLayout.findViewById<TextView>(R.id.tvJoin).text = "Join us in monthly gaming events that will keep you on the edge of your seat! Compete with other gamers."
                }
            }
        } else {
            linearLayout.visibility = View.GONE
        }
    }
}