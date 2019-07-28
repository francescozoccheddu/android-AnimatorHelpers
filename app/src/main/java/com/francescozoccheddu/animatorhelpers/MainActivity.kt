package com.francescozoccheddu.animatorhelpers

import android.app.Activity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class MainActivity : Activity() {

    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val target = findViewById<View>(R.id.target)
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?) = true

            private fun setTarget(event: MotionEvent) {
                target.x = event.x
                target.y = event.y
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
    }

    override fun onTouchEvent(event: MotionEvent?) = gestureDetector.onTouchEvent(event)

}
