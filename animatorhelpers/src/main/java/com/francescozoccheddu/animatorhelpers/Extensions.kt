package com.francescozoccheddu.animatorhelpers

import kotlin.reflect.KProperty

inline fun <Type> AnimatedValue<Type>.fromTo(from: Type, to: Type) {
    jumpTo(from)
    value = to
}

inline fun <Type> AnimatedValue<Type>.jumpTo(value: Type) {
    this.value = value
    reach()
}

inline fun <Type> TargetedValue<Type>.jumpToAndContinue(value: Type) {
    fromTo(value, target)
}


inline fun <Type> ReadOnlyAnimatedValue<Type>.asReadOnly() = ReadOnlyAnimatedValueWrapper(this)