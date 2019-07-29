package com.francescozoccheddu.animatorhelpers

class SmoothColor(initialValue: Int) : SmoothValue<Int>(initialValue) {

    private companion object {
        private const val SNAP = 0.5f
    }

    private var r = initialValue.red
    private var g = initialValue.green
    private var b = initialValue.blue
    private var a = initialValue.alpha

    override fun reach() {
        super.reach()
        r = target.red
        g = target.green
        b = target.blue
        a = target.alpha
    }

    override fun update(): Int {
        r = smooth(r, target.red, SNAP)
        g = smooth(g, target.green, SNAP)
        b = smooth(b, target.blue, SNAP)
        a = smooth(a, target.alpha, SNAP)
        return rgba(r, g, b, a)
    }

}