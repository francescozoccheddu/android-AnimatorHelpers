package com.francescozoccheddu.animatorhelpers

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import kotlin.math.abs
import kotlin.math.roundToLong

abstract class ABValue<Type>(initialValue: Type) : TargetedValue<Type>(initialValue) {

    enum class DurationMode {
        CONSTANT_DURATION, CONSTANT_SPEED
    }

    override final fun reach() {
        super.reach()
        animator.cancel()
    }

    var durationMode = DurationMode.CONSTANT_DURATION
        private set

    private var durationValue = 0.5f

    var duration
        get() = if (durationMode == DurationMode.CONSTANT_DURATION) durationValue else throw IllegalStateException("Not in constant duration mode")
        set(value) {
            if (value < 0.0f)
                throw IllegalArgumentException("'${this::duration.name}' cannot be negative")
            if (durationMode != DurationMode.CONSTANT_DURATION || durationValue != value) {
                durationMode = DurationMode.CONSTANT_DURATION
                durationValue = value
                animateTo(target)
            }
        }

    var speed
        get() = if (durationMode == DurationMode.CONSTANT_SPEED) durationValue else throw IllegalStateException("Not in constant acceleration mode")
        set(value) {
            if (value <= 0.0f)
                throw IllegalArgumentException("'${this::speed.name}' must be positive")
            if (durationMode != DurationMode.CONSTANT_SPEED || durationValue != value) {
                durationMode = DurationMode.CONSTANT_SPEED
                durationValue = value
                animateTo(target)
            }
        }

    var interpolator: TimeInterpolator
        get() = animator.interpolator
        set(value) {
            animator.interpolator = value
        }

    private var from = initialValue

    override final fun animateToTarget() {
        from = _value
        animator.cancel()
        animator.duration = (when (durationMode) {
            DurationMode.CONSTANT_DURATION -> durationValue
            DurationMode.CONSTANT_SPEED -> abs(getDifference(from, target)) / speed
        } * 1000f).roundToLong()
        animator.start()
    }

    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        addUpdateListener {
            _value = interpolate(from, target, animatedValue as Float)
        }
    }

    protected abstract fun getDifference(a: Type, b: Type): Float

    protected abstract fun interpolate(from: Type, to: Type, progress: Float): Type

}