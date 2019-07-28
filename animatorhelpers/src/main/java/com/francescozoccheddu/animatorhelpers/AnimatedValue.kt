package com.francescozoccheddu.animatorhelpers

import kotlin.reflect.KProperty

abstract class AnimatedValue<Type>(initialValue: Type) {

    open var value = initialValue

    var onUpdate: ((Type) -> Unit)? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = this.value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Type) {
        this.value = value
    }

    abstract val running: Boolean

    abstract fun reach()

}