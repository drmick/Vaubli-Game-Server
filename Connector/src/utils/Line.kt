//package utils
//
//import com.github.davidmoten.rtree.geometry.Geometries
//import com.github.davidmoten.rtree.geometry.Line
//import com.github.davidmoten.rtree.geometry.Point
//
///**
// * Created by Maks on 03.05.2019.
// */
//class Line(private val point1: Point, private val point2: Point) {
//    fun povorot(ang: Double): Line? {
//        return Geometries.line(
//                point1.x() * Math.cos(ang) - point1.y() * Math.sin(ang),
//                point1.x() * Math.sin(ang) + point1.y() * Math.cos(ang),
//                point2.x() * Math.cos(ang) - point2.y() * Math.sin(ang),
//                point2.x() * Math.sin(ang) + point2.y() * Math.cos(ang)
//        )
//    }
//}