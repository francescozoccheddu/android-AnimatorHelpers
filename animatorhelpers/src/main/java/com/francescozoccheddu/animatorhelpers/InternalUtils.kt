package com.francescozoccheddu.animatorhelpers

import android.graphics.Color
import kotlin.math.abs
import kotlin.math.roundToInt

internal inline fun lerp(from: Float, to: Float, progress: Float) = from * (1f - progress) + to * progress

internal inline fun lerp(from: Int, to: Int, progress: Float) =
    lerp(from.toFloat(), to.toFloat(), progress).roundToInt()

internal inline fun Float.isAlmost(target: Float, tolerance: Float) = abs(this - target) <= tolerance

internal inline val Int.red get() = Color.red(this).toFloat()
internal inline val Int.green get() = Color.green(this).toFloat()
internal inline val Int.blue get() = Color.blue(this).toFloat()
internal inline val Int.alpha get() = Color.alpha(this).toFloat()

internal inline fun rgba(r: Float, g: Float, b: Float, a: Float) =
    rgba(r.roundToInt(), g.roundToInt(), b.roundToInt(), a.roundToInt())

internal inline fun rgba(r: Int, g: Int, b: Int, a: Int) =
    Color.argb(a, r, g, b)
