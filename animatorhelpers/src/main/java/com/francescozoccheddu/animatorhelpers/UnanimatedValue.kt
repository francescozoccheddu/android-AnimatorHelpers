package com.francescozoccheddu.animatorhelpers

class UnanimatedValue<Type>(initialValue: Type) : AnimatedValue<Type>(initialValue) {

    override val running
        get() = false

    override fun reach() {}

}