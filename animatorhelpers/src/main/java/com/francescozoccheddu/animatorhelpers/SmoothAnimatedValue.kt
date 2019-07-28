package com.francescozoccheddu.animatorhelpers

import android.animation.TimeAnimator
import kotlin.math.abs
import kotlin.math.min

class SmoothAnimatedValue(initialValue: Float) : TargetedAnimatedValue<Float>(initialValue) {

    override fun animateTo(value: Float) {
        super.animateTo(value)
        if (!animator.isRunning)
            animator.start()
    }

    override fun reach() {
        super.reach()
        animator.cancel()
    }

    var snap = Float.MIN_VALUE
        set(value) {
            if (value < 0.0f)
                throw IllegalArgumentException("'${this::snap.name}' cannot be negative")
            field = value
        }

    var smoothing = 0.5f
        set(value) {
            if (value < 0.0f)
                throw IllegalArgumentException("'${this::smoothing.name}' cannot be negative")
            field = value
        }

    private val animator = TimeAnimator().apply {
        setTimeListener { _, _, elapsed ->
            if (smoothing == 0f)
                reach()
            else {
                val progress = min((elapsed / 1000f) / smoothing, 1f)
                val value = _value * (1f - progress) + target * progress
                if (abs(value - target) <= snap)
                    reach()
                else
                    _value = value
            }
        }
    }

}