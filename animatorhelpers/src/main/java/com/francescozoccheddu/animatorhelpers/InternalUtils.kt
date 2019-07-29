package com.francescozoccheddu.animatorhelpers

import android.animation.TimeAnimator
import android.graphics.Color
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

internal inline fun lerp(from: Float, to: Float, progress: Float) = from * (1f - progress) + to * progress

internal inline fun lerp(from: Int, to: Int, progress: Float) =
    lerp(from.toFloat(), to.toFloat(), progress).roundToInt()

internal inline fun Float.isAlmost(target: Float, tolerance: Float) = abs(this - target) <= tolerance

internal inline fun Float.clampAbsValue(maxAbsValue: Float) =
    if (this > maxAbsValue)
        maxAbsValue
    else if (this < -maxAbsValue)
        -maxAbsValue
    else
        this

internal inline val Int.red get() = Color.red(this).toFloat()
internal inline val Int.green get() = Color.green(this).toFloat()
internal inline val Int.blue get() = Color.blue(this).toFloat()
internal inline val Int.alpha get() = Color.alpha(this).toFloat()

internal inline fun rgba(r: Float, g: Float, b: Float, a: Float) =
    rgba(r.roundToInt(), g.roundToInt(), b.roundToInt(), a.roundToInt())

internal inline fun rgba(r: Int, g: Int, b: Int, a: Int) =
    Color.argb(a, r, g, b)

internal class Ticker {

    var onTick: ((Float) -> Unit)? = null

    var maxElapsedTime = 1f
        set(value) {
            if (value < maxTickLength)
                throw IllegalArgumentException("'${this::maxElapsedTime.name}' cannot be smaller than '${this::maxTickLength.name}'")
            field = value
        }

    var maxTickLength = 1f / 10f
        set(value) {
            if (value <= 0.0f)
                throw IllegalArgumentException("'${this::maxElapsedTime.name}' must be positive")
            field = value
        }

    var running = false
        set(value) {
            if (field != value) {
                field = value
                if (running)
                    interpolator.start()
                else
                    interpolator.cancel()
            }
        }

    private val interpolator = TimeAnimator().apply {
        setTimeListener { _, _, elapsedMs ->
            var elapsed = min(elapsedMs / 1000f, maxElapsedTime)
            while (elapsed > maxTickLength) {
                onTick?.invoke(maxTickLength)
                elapsed -= maxTickLength
            }
            if (elapsed > 0f)
                onTick?.invoke(elapsed)
        }
    }

}