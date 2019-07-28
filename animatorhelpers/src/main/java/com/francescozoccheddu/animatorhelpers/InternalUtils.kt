package com.francescozoccheddu.animatorhelpers

import android.graphics.Color
import kotlin.math.abs
import kotlin.math.roundToInt

internal inline fun lerp(from: Float, to: Float, progress: Float) = from * (1f - progress) + to * progress

internal inline fun Float.isAlmost(target: Float, tolerance: Float) = abs(this - target) <= tolerance

internal inline val Int.red get() = Color.red(this).toFloat()
internal inline val Int.green get() = Color.green(this).toFloat()
internal inline val Int.blue get() = Color.blue(this).toFloat()
internal inline val Int.alpha get() = Color.alpha(this).toFloat()

internal inline fun rgba(r: Float, g: Float, b: Float, a: Float) =
    Color.argb(a.roundToInt(), r.roundToInt(), g.roundToInt(), b.roundToInt())