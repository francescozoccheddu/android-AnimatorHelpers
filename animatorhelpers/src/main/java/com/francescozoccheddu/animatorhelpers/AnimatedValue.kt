package com.francescozoccheddu.animatorhelpers

import kotlin.reflect.KProperty

abstract class AnimatedValue<Type>(initialValue: Type) : ReadOnlyObservableAnimatedValue<Type> {

    protected var _value = initialValue
        set(value) {
            if (field != value) {
                field = value
                onUpdate?.invoke(this)
            }
        }

    override final var value: Type
        get() = _value
        set(value) {
            if (value != _value) {
                animateTo(value)
            }
        }

    override final var onUpdate: ((ReadOnlyAnimatedValue<Type>) -> Unit)? = null

    protected open fun animateTo(value: Type) {}

    override final operator fun getValue(thisRef: Any?, property: KProperty<*>) = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Type) {
        this.value = value
    }

    abstract fun reach()

}