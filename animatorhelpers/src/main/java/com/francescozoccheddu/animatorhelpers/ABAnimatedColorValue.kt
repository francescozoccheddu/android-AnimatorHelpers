package com.francescozoccheddu.animatorhelpers

import android.graphics.Color
import kotlin.math.abs
import kotlin.math.roundToInt

class ABAnimatedColorValue(initialValue: Int) : ABAnimatedValue<Int>(initialValue) {

    override fun getDifference(a: Int, b: Int): Float {
        val r = Color.red(a) - Color.red(b)
        val g = Color.green(a) - Color.green(b)
        val b = Color.blue(a) - Color.blue(b)
        val a = Color.alpha(a) - Color.alpha(b)
        return (abs(r) + abs(g) + abs(b) + abs(a)).toFloat()
    }

    override fun interpolate(from: Int, to: Int, progress: Float): Int {
        val r = lerp(Color.red(from).toFloat(), Color.red(to).toFloat(), progress).roundToInt()
        val g = lerp(Color.green(from).toFloat(), Color.green(to).toFloat(), progress).roundToInt()
        val b = lerp(Color.blue(from).toFloat(), Color.blue(to).toFloat(), progress).roundToInt()
        val a = lerp(Color.alpha(from).toFloat(), Color.alpha(to).toFloat(), progress).roundToInt()
        return Color.argb(a, r, g, b)
    }

}