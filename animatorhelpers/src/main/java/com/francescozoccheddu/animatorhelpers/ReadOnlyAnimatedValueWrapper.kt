package com.francescozoccheddu.animatorhelpers

class ReadOnlyAnimatedValueWrapper<Type>(private val animatedValue: ReadOnlyAnimatedValue<Type>) :
    ReadOnlyAnimatedValue<Type> by animatedValue