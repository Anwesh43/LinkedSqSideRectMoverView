package com.anwesh.uiprojects.sqsiderectmoverview

/**
 * Created by anweshmishra on 13/01/19.
 */

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import android.view.View
import android.view.MotionEvent

val nodes : Int = 5
val rects : Int = 4
val sizeFactor : Float = 2.7f
val rSizeFactor : Float = 3.0f
val scDiv : Double = 0.51
val scGap : Float = 0.05f
val foreColor : Int = Color.parseColor("#4CAF50")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateScale(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawSSRMNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sf : Float = 1f - 2 * (i % 2)
    val rSize : Float = size / rSizeFactor
    paint.color = foreColor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val x : Float = (size + rSize + w/2) * sf * sc2
    val deg : Float = 360f * sc2 * sf
    val rh : Float = rSize
    val rw : Float = 2 * rSize
    save()
    translate(w/2 + x, gap * (i + 1))
    rotate(deg)
    drawRect(RectF(-size, -size, size, size), paint)
    for (j in 0..(rects - 1)) {
        val sc : Float = sc1.divideScale(j, rects)
        save()
        rotate(90f * j)
        translate(0f, size - rh + rh * sc)
        drawRect(RectF(-rw/2, 0f, rw/2, rh), paint)
        restore()
    }
    restore()
}