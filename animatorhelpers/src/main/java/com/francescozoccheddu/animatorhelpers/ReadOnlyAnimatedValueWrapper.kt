package com.francescozoccheddu.animatorhelpers

private class ReadOnlyAnimatedValueWrapper<Type>(animatedValue: ReadOnlyAnimatedValue<Type>) :
    ReadOnlyAnimatedValue<Type> by animatedValue

fun <Type> ReadOnlyAnimatedValue<Type>.asReadOnly(): ReadOnlyAnimatedValue<Type> = ReadOnlyAnimatedValueWrapper(this)