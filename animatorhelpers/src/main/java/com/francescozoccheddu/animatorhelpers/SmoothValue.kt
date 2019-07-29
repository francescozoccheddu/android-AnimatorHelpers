package com.francescozoccheddu.animatorhelpers

import android.animation.TimeAnimator
import kotlin.math.min

abstract class SmoothValue<Type>(initialValue: Type) : TargetedValue<Type>(initialValue) {

    override final fun animateToTarget() {
        if (!animator.isRunning)
            animator.start()
    }

    override fun reach() {
        super.reach()
        animator.cancel()
    }

    var smoothness = 0.5f
        set(value) {
            if (value < 0.0f)
                throw IllegalArgumentException("'${this::smoothness.name}' cannot be negative")
            field = value
        }

    private var lastProgress: Float? = null

    private val animator = TimeAnimator().apply {
        setTimeListener { _, _, elapsed ->
            if (smoothness == 0f)
                reach()
            else {
                lastProgress = min((elapsed / 1000f) / smoothness, 1f)
                update()
                lastProgress = null
                if (!running)
                    reach()
            }
        }
    }

    protected abstract fun update()

    protected fun smooth(from: Float, to: Float, snap: Float): Float {
        val progress = lastProgress
        if (progress == null)
            throw IllegalStateException("'${this::smooth.name}' cannot be called outside '${this::update.name}'")
        else {
            val value = lerp(from, to, progress)
            return if (value.isAlmost(to, snap)) to else value
        }
    }

}