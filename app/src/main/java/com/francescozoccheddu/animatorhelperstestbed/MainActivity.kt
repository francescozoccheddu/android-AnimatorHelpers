package com.francescozoccheddu.animatorhelperstestbed

import android.app.Activity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.francescozoccheddu.animatorhelpers.SmoothFloat

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val target = findViewById<View>(R.id.target)
        var tx by SmoothFloat(target.x).apply {
            onUpdate = {
                target.x = it - target.width / 2f
            }
            snap = 1f
            smoothing = 0.1f
        }
        var ty by SmoothFloat(target.y).apply {
            onUpdate = {
                target.y = it - target.height / 2f
            }
            snap = 1f
            smoothing = 0.1f
        }
        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?) = true

            private fun setTarget(event: MotionEvent) {
                tx = event.x
                ty = event.y
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                if (e != null) {
                    setTarget(e)
                }
                return true
            }

            override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
                if (e2 != null) {
                    setTarget(e2)
                }
                return true
            }

        }).apply {
            setIsLongpressEnabled(false)
            setOnDoubleTapListener(null)
        }
        findViewById<View>(R.id.root).setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

}
