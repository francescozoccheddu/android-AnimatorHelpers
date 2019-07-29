package com.francescozoccheddu.animatorhelpers

import kotlin.reflect.KProperty

interface ReadOnlyAnimatedValue<Type> {

    val value: Type

    var onUpdate: ((ReadOnlyAnimatedValue<Type>) -> Unit)?

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Type

    val running: Boolean

}