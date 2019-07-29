package com.francescozoccheddu.animatorhelpers

class SpringFloat(initialValue: Float) : SpringValue<Float>(initialValue) {

    var snap
        get() = spring.snap
        set(value) {
            spring.snap = value
        }

    private val spring = Spring(initialValue).apply {
        snap = Float.MIN_VALUE
    }

    override fun reach() {
        super.reach()
        spring.value = target
    }

    override fun getSprings() = listOf(spring)

    override fun getCurrentValue() = spring.value

    override fun animateToTarget() {
        spring.target = target
        super.animateToTarget()
    }

}