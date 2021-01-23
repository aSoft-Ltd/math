package tz.co.asoft

import kotlinx.css.Color
import org.w3c.dom.CanvasRenderingContext2D
import kotlin.math.PI

fun CanvasRenderingContext2D.draw(point: Point2<*>, radius: Double = 5.0, color: Color = Color.white) {
    fillStyle = color.value
    strokeStyle = color.value
    beginPath()
    arc(point.x.toDouble(), point.y.toDouble(), radius, startAngle = 0.0, endAngle = 2 * PI)
    fill()
}