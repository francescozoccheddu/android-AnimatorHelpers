package com.francescozoccheddu.animatorhelpers

abstract class TargetedAnimatedValue<Type>(initialValue: Type) : AnimatedValue<Type>(initialValue) {

    var target: Type = initialValue
        private set

    override final val running: Boolean
        get() = target != value

    override fun animateTo(value: Type) {
        target = value
    }

    override fun reach() {
        _value = target
    }

}