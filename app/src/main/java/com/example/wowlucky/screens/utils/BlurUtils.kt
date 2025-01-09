package com.example.wowlucky.screens.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.wowlucky.screens.game.MainFragment

object BlurUtils {

    fun Context.sendBlurAction(type: String) {
        val intent = Intent(MainFragment.ACTION_BLUR).apply {
            putExtra(MainFragment.EXTRA_BLUR_TYPE, type)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }
    fun removeBlur(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setRenderEffect(null)
        } else {
            val originalBackground: Drawable? = view.background
            view.background = originalBackground
        }
    }
    fun applyBlurWithRenderEffect(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val blurEffect = RenderEffect.createBlurEffect(10f, 10f, Shader.TileMode.CLAMP)
            view.setRenderEffect(blurEffect)
        }
    }

    fun applyBlur(context: Context, view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            applyBlurWithRenderEffect(view)
        } else {
            applyBlurWithRenderScript(context, view)
        }
    }

    fun getBitmapFromView(view: View): Bitmap {
        val width = view.width
        val height = view.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun applyBlurWithRenderScript(context: Context, view: View) {
        val renderScript = RenderScript.create(context)
        val bitmap = getBitmapFromView(view)
        val blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        val outputBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val allocIn = Allocation.createFromBitmap(renderScript, bitmap)
        val allocOut = Allocation.createFromBitmap(renderScript, outputBitmap)
        blurScript.setRadius(10f)
        blurScript.setInput(allocIn)
        blurScript.forEach(allocOut)
        allocOut.copyTo(outputBitmap)
        view.background = android.graphics.drawable.BitmapDrawable(context.resources, outputBitmap)
        renderScript.destroy()
    }
}