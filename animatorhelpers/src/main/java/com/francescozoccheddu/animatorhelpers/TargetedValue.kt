package com.francescozoccheddu.animatorhelpers

abstract class TargetedValue<Type>(initialValue: Type) : AnimatedValue<Type>(initialValue) {

    var target: Type = initialValue
        private set

    override final val running: Boolean
        get() = target != value

    override final fun animateTo(value: Type) {
        if (target != value) {
            target = value
            if (running)
                animateToTarget()
        }
    }

    protected abstract fun animateToTarget()

    override fun reach() {
        _value = target
    }

}