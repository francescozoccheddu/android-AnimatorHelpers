package com.francescozoccheddu.animatorhelpers

inline fun <Type> AnimatedValue<Type>.fromTo(from: Type, to: Type) {
    jumpTo(from)
    value = to
}

inline fun <Type> AnimatedValue<Type>.jumpTo(value: Type) {
    this.value = value
    reach()
}

inline fun <Type> TargetedValue<Type>.from(value: Type) {
    fromTo(value, target)
}
