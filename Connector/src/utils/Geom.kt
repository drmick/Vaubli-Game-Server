//package utils
//
//import com.github.davidmoten.rtree.geometry.Geometries
//import com.github.davidmoten.rtree.geometry.Point
//
///**
// * Created by Maks on 24.04.2019.
// */
//object Geom {
//    fun polar(distance: Double, angleDegrees: Double): Point {
//        val angleRadians = Math.toRadians(angleDegrees)
//        return Geometries.point(Math.cos(angleRadians) * distance, Math.sin(angleRadians) * distance)
//    }
//
//    fun povorot(x: Double, y: Double, ang: Double): Point {
//        return Geometries.point(x * Math.cos(ang) - y * Math.sin(ang), x * Math.sin(ang) + y * Math.cos(ang))
//    }
//
//    fun sumPoints(position: Point, newPosition: Point): Point {
//        return Geometries.point(position.x() + newPosition.x(), position.y() + newPosition.y())
//    }
//}