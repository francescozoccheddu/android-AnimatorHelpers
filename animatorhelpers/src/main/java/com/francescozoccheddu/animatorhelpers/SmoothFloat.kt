package com.francescozoccheddu.animatorhelpers

class SmoothFloat(initialValue: Float) : SmoothValue<Float>(initialValue) {

    var snap = Float.MIN_VALUE
        set(value) {
            if (value < 0.0f)
                throw IllegalArgumentException("'${this::snap.name}' cannot be negative")
            field = value
        }

    override fun update() {
        _value = smooth(_value, target, snap)
    }

}