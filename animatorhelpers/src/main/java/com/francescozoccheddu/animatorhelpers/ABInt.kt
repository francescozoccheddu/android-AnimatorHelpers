package com.francescozoccheddu.animatorhelpers

import kotlin.math.roundToInt

class ABInt(initialValue: Int) : ABValue<Int>(initialValue) {

    override fun getDifference(a: Int, b: Int) = (a - b).toFloat()

    override fun interpolate(from: Int, to: Int, progress: Float) =
        lerp(from.toFloat(), to.toFloat(), progress).roundToInt()

}