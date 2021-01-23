package tz.co.asoft

import kotlinx.css.Color
import org.w3c.dom.CanvasRenderingContext2D

fun CanvasRenderingContext2D.draw(vec: Vec2<*>, at: Point2<*> = Point2.origin, width: Double = 1.0, color: Color = Color.white) {
    strokeStyle = color.value
    lineWidth = width
    beginPath()
    moveTo(at.x.toDouble(), at.y.toDouble())
    val endPoint = vec + at.asVec()
    lineTo(endPoint.x, endPoint.y)
    stroke()
}