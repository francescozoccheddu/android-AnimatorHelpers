package com.francescozoccheddu.animatorhelperstestbed

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import com.francescozoccheddu.animatorhelpers.SmoothFloat
import com.francescozoccheddu.animatorhelpers.SpringColor
import com.francescozoccheddu.animatorhelpers.SpringFloat

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val target = findViewById<View>(R.id.v_target)

        fun TextView.setRunningColor(running: Boolean) {
            setTextColor(resources.getColor(if (running) R.color.label_running else R.color.label_idle))
        }

        // Position
        run {
            var tx by SmoothFloat(target.x).apply {
                val label = findViewById<TextView>(R.id.tv_pos_x)
                onUpdate = {
                    target.x = it.value - target.width / 2f
                    label.setRunningColor(it.running)
                    label.text = "${it.value}"
                }
                smoothness = 0.1f
                snap = 0.1f
                onUpdate?.invoke(this)
            }
            var ty by SmoothFloat(target.y).apply {
                val label = findViewById<TextView>(R.id.tv_pos_y)
                onUpdate = {
                    target.y = it.value - target.height / 2f
                    label.setRunningColor(it.running)
                    label.text = "${it.value}"
                }
                smoothness = 0.1f
                snap = 0.1f
                onUpdate?.invoke(this)
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
            findViewById<View>(R.id.fl_root).setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
            }
        }


        // Rotation
        run {
            var rot by SpringFloat(target.rotation).apply {
                val label = findViewById<TextView>(R.id.tv_rot)
                onUpdate = {
                    target.rotation = it.value
                    label.setRunningColor(it.running)
                    label.text = "${it.value}°"
                }
                acceleration = 10f
                maxVelocity = 5000f
                snap = 0.1f
                onUpdate?.invoke(this)
            }

            findViewById<SeekBar>(R.id.sb_rot).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    rot = progress.toFloat()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            })
        }

        // Color
        run {
            var col by SpringColor(Color.RED).apply {
                val label = findViewById<TextView>(R.id.tv_col)
                onUpdate = {
                    target.setBackgroundColor(it.value)
                    label.setRunningColor(it.running)
                    label.text = "#${Integer.toHexString(it.value)}"
                }
                acceleration = 10f
                maxVelocity = 5000f
                onUpdate?.invoke(this)
            }

            findViewById<SeekBar>(R.id.sb_col).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                val array = FloatArray(3).apply {
                    this[1] = 1f
                    this[2] = 1f
                }

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    array[0] = progress.toFloat()
                    col = Color.HSVToColor(array)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            })
        }

    }

}
