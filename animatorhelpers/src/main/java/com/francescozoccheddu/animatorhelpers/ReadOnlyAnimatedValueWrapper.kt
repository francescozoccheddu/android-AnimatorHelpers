package com.francescozoccheddu.animatorhelpers

private class ReadOnlyAnimatedValueWrapper<Type>(animatedValue: ReadOnlyAnimatedValue<Type>) :
    ReadOnlyAnimatedValue<Type> by animatedValue

private class ReadOnlyObservableAnimatedValueWrapper<Type>(animatedValue: ReadOnlyObservableAnimatedValue<Type>) :
    ReadOnlyObservableAnimatedValue<Type> by animatedValue

fun <Type> ReadOnlyAnimatedValue<Type>.asReadOnly(): ReadOnlyAnimatedValue<Type> =
    ReadOnlyAnimatedValueWrapper(this)

fun <Type> ReadOnlyObservableAnimatedValue<Type>.asObservableReadOnly(): ReadOnlyObservableAnimatedValue<Type> =
    ReadOnlyObservableAnimatedValueWrapper(this)