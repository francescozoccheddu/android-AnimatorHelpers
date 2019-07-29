package com.francescozoccheddu.animatorhelpers

class SpringColor(initialValue: Int) : SpringValue<Int>(initialValue) {

    private companion object {
        private inline operator fun Int.get(component: Int) =
            when (component) {
                0 -> red
                1 -> green
                2 -> blue
                3 -> alpha
                else -> throw IllegalArgumentException()
            }
    }

    private val springs = (0 until 4).map { Spring(initialValue[it]).apply { snap = 0.5f } }.toList()

    override fun animateToTarget() {
        for (i in 0 until 4)
            springs[i].target = target[i]
        super.animateToTarget()
    }

    override fun reach() {
        for (i in 0 until 4)
            springs[i].value = target[i]
        super.reach()
    }

    override fun getSprings() = springs

    override fun getCurrentValue() = rgba(springs[0].value, springs[1].value, springs[2].value, springs[3].value)

}