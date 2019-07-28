package com.francescozoccheddu.animatorhelpers

import kotlin.math.abs

class ABColor(initialValue: Int) : ABValue<Int>(initialValue) {

    override fun getDifference(a: Int, b: Int): Float {
        val red = a.red - b.red
        val green = a.green - b.green
        val blue = a.blue - b.blue
        val alpha = a.alpha - b.alpha
        return (abs(red) + abs(green) + abs(blue) + abs(alpha))
    }

    override fun interpolate(from: Int, to: Int, progress: Float): Int {
        val r = lerp(from.red, to.red, progress)
        val g = lerp(from.green, to.green, progress)
        val b = lerp(from.blue, to.blue, progress)
        val a = lerp(from.alpha, to.alpha, progress)
        return rgba(r, g, b, a)
    }

}