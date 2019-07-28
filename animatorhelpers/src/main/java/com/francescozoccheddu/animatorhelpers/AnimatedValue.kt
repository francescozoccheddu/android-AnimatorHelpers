package com.francescozoccheddu.animatorhelpers

import kotlin.reflect.KProperty

abstract class AnimatedValue<Type>(initialValue: Type) {

    protected var _value = initialValue
        set(value) {
            if (field != value) {
                field = value
                onUpdate?.invoke(value)
            }
        }

    var value: Type
        get() = _value
        set(value) {
            if (value != _value) {
                animateTo(value)
            }
        }

    protected open fun animateTo(value: Type) {}

    var onUpdate: ((Type) -> Unit)? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = this.value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Type) {
        this.value = value
    }

    abstract val running: Boolean

    abstract fun reach()

}