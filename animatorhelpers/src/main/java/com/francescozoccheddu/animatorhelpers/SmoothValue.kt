package com.francescozoccheddu.animatorhelpers

import kotlin.math.min

abstract class SmoothValue<Type>(initialValue: Type) : TargetedValue<Type>(initialValue) {

    override final fun animateToTarget() {
        ticker.running = true
    }

    override fun reach() {
        super.reach()
        ticker.running = false
    }

    var smoothness = 0.5f
        set(value) {
            if (value < 0.0f)
                throw IllegalArgumentException("'${this::smoothness.name}' cannot be negative")
            field = value
        }

    private var lastProgress: Float? = null

    private val ticker = Ticker().apply {
        onTick = { elapsed ->
            if (smoothness == 0f)
                reach()
            else {
                lastProgress = min(elapsed / smoothness, 1f)
                _value = update()
                lastProgress = null
                if (!running)
                    reach()
            }
        }
        maxElapsedTime = 1f / 10f
        maxTickLength = 1f / 30f
    }

    protected abstract fun update(): Type

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