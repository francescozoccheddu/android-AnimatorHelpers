package com.francescozoccheddu.animatorhelpers

class UnanimatedValue<Type>(initialValue: Type) : AnimatedValue<Type>(initialValue) {

    override var value: Type
        get() = super.value
        set(value) {
            super.value = value
            onUpdate?.invoke(value)
        }

    override val running = false

    override fun reach() {}

}