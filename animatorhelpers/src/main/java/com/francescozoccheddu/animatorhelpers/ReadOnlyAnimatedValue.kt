package com.francescozoccheddu.animatorhelpers

import kotlin.reflect.KProperty

interface ReadOnlyAnimatedValue<Type> {

    val value: Type

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Type

    val running: Boolean

}

interface ReadOnlyObservableAnimatedValue<Type> : ReadOnlyAnimatedValue<Type> {

    var onUpdate: ((ReadOnlyAnimatedValue<Type>) -> Unit)?

}
