package tz.co.asoft

interface ICartesianCoordinate2d {
    val backgroundColor: Color
    val foregroundColor: Color
    fun clear()
    fun draw(point: Point2<*>, radius: Double = 1.0, color: Color = foregroundColor)
    fun draw(vec: Vec2<*>, at: Point2<*> = Point2.origin, width: Double = 1.0, color: Color = foregroundColor)
}