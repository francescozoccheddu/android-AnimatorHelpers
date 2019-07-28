package com.francescozoccheddu.animatorhelpers

fun <Type> AnimatedValue<Type>.fromTo(from: Type, to: Type) {
    jumpTo(from)
    value = to
}

fun <Type> AnimatedValue<Type>.jumpTo(value: Type) {
    this.value = value
    reach()
}

fun <Type> TargetedAnimatedValue<Type>.jumpToAndContinue(value: Type) {
    fromTo(value, target)
}

