package com.francescozoccheddu.animatorhelpers

class ABFloat(initialValue: Float) : ABValue<Float>(initialValue) {

    override fun getDifference(a: Float, b: Float) = a - b

    override fun interpolate(from: Float, to: Float, progress: Float) = lerp(from, to, progress)

}