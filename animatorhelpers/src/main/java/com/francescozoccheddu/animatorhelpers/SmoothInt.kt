package com.francescozoccheddu.animatorhelpers

import kotlin.math.roundToInt

class SmoothInt(initialValue: Int) : SmoothValue<Int>(initialValue) {

    private companion object {
        private const val SNAP = 0.5f
    }

    private var rawValue = initialValue.toFloat()

    override fun reach() {
        super.reach()
        rawValue = value.toFloat()
    }

    override fun update() : Int {
        rawValue = smooth(rawValue, target.toFloat(), SNAP)
        return rawValue.roundToInt()
    }

}