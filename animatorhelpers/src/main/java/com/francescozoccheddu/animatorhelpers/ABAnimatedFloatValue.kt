package com.francescozoccheddu.animatorhelpers

class ABAnimatedFloatValue(initialValue: Float) : ABAnimatedValue<Float>(initialValue) {

    override fun getDifference(a: Float, b: Float) = a - b

    override fun interpolate(from: Float, to: Float, progress: Float) = lerp(from, to, progress)

}