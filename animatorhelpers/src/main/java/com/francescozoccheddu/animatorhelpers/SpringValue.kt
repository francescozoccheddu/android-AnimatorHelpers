package com.francescozoccheddu.animatorhelpers

abstract class SpringValue<Type>(initialValue: Type) : TargetedValue<Type>(initialValue) {

    private companion object {
        private const val VELOCITY_SNAP_FACTOR = 2f
    }

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

    var speed = 1f
        set(value) {
            if (value <= 0.0f)
                throw IllegalArgumentException("'${this::speed.name}' must be positive")
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
            val n1 = velocity - (value - target) * (speed * speed * elapsed)
            val n2 = 1f + speed * elapsed
            velocity = (n1 / (n2 * n2)).clampAbsValue(maxVelocity)
            value += velocity * elapsed
            if (snap > 0f && value.isAlmost(target, snap) && velocity.isAlmost(0f, snap * VELOCITY_SNAP_FACTOR)) {
                value = target
                velocity = 0f
            }
        }

    }

    protected abstract fun getSprings(): Iterable<Spring>

    protected abstract fun getCurrentValue(): Type

}