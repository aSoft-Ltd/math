package tz.co.asoft

import org.w3c.dom.CanvasRenderingContext2D

class CartesianCoordinate2d(
    private val context: CanvasRenderingContext2D,
    override val backgroundColor: Color = Color.Black,
    override val foregroundColor: Color = Color.White
) : ICartesianCoordinate2d {
    private val canvas = context.canvas

    val width = canvas.width.toDouble()
    val height = canvas.height.toDouble()

    override fun clear() {
        context.clearRect(0.0, 0.0, width, height)
        context.fillStyle = backgroundColor().value
        context.fillRect(0.0, 0.0, width, height)
    }

    override fun draw(point: Point2<*>, radius: Double, color: Color) {
        context.draw(point, radius, color())
    }

    override fun draw(vec: Vec2<*>, at: Point2<*>, width: Double, color: Color) {
        context.draw(vec, at, width, color())
    }
}