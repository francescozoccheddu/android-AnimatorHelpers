package com.francescozoccheddu.animatorhelpers

import kotlin.math.roundToInt

class SpringInt(initialValue: Int) : SpringValue<Int>(initialValue) {

    private val spring = Spring(initialValue.toFloat()).apply {
        snap = 0.5f
    }

    override fun animateToTarget() {
        spring.target = target.toFloat()
        super.animateToTarget()
    }

    override fun reach() {
        super.reach()
        spring.value = target.toFloat()
    }

    override fun getSprings() = listOf(spring)

    override fun getCurrentValue() = spring.value.roundToInt()

}