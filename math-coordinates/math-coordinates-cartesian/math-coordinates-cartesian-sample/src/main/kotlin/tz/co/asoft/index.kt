package tz.co.asoft

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

fun main() {
    val canvas = document.getElementById("canvas") as HTMLCanvasElement
    val context = canvas.getContext("2d") as CanvasRenderingContext2D
    val dV = Vec(0.1, 0.1)
    var u = Vec(20, 30) + dV
    val point = Point(100, 50)

    val coordinate: ICartesianCoordinate2d = CartesianCoordinate2d(context)

    var lastDelta = 0.0
    fun update(delta: Double) {
        console.log("Animating ${delta - lastDelta}")
        u += dV
        coordinate.apply {
            clear()
            draw(point)
            draw(u)
            draw(u, point, width = 1.0)
        }
        lastDelta = delta
        window.requestAnimationFrame(::update)
    }

    update(0.0)
}