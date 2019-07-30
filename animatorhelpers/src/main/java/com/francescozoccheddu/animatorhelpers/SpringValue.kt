package com.francescozoccheddu.animatorhelpers

abstract class SpringValue<Type>(initialValue: Type) : TargetedValue<Type>(initialValue) {

    override fun animateToTarget() {
        ticker.running = true
    }

    override fun reach() {
        super.reach()
        ticker.running = false
    }

    var maxVelocity = 100f
        set(value) {
            if (value <= 0.0f)
                throw IllegalArgumentException("'${this::maxVelocity.name}' must be positive")
            field = value
        }

    var acceleration = 1f
        set(value) {
            if (value <= 0.0f)
                throw IllegalArgumentException("'${this::acceleration.name}' must be positive")
            field = value
        }

    private val ticker = Ticker().apply {
        onTick = { elapsed ->
            for (spring in getSprings())
                spring.update(elapsed)
            _value = getCurrentValue()
            if (!running)
                reach()
        }
    }

    protected inner class Spring(initialValue: Float) {

        private var velocity = 0f
        var value = initialValue
        var target = initialValue
        var snap = 0f
            set(value) {
                if (value <= 0.0f)
                    throw IllegalArgumentException("'${this::snap.name}' must be positive")
                field = value
            }

        fun update(elapsed: Float) {
            val n1 = velocity - (value - target) * (acceleration * acceleration * elapsed)
            val n2 = 1f + acceleration * elapsed
            velocity = (n1 / (n2 * n2)).clampAbsValue(maxVelocity)
            value += velocity * elapsed
            if (snap > 0f && value.isAlmost(target, snap)) {
                value = target
                velocity = 0f
            }
        }

    }

    protected abstract fun getSprings(): Iterable<Spring>

    protected abstract fun getCurrentValue(): Type

}