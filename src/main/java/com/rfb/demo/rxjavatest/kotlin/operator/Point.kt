package com.rfb.demo.rxjavatest.kotlin.operator

class Point(var x:Int, var y:Int) {

    operator fun plus(other:Point) : Point{
        return Point(x+other.x, y+other.y)
    }

    operator fun dec():Point{
        return Point(x-1, y-1)
    }

    operator fun times(other: Point):Point{
        return Point(x*other.x, y*other.y)
    }

    operator fun minus(other:Point) :Point{
        return Point(x-other.x, y-other.y)
    }

    operator fun div(other:Point) :Point{
        return Point(x/other.x, y/other.y)
    }

    operator fun rem(other:Point) :Point{
        return Point(x%other.x, y%other.y)
    }

    operator fun plusAssign(other: Point){
        x += other.x
        y += other.y
    }

    operator fun minusAssign(other: Point){
        x -= other.x
        y -= other.y
    }

    operator fun timesAssign(other: Point){
        x *= other.x
        y *= other.y
    }

    operator fun divAssign(other: Point){
        x /= other.x
        y /= other.y
    }

    operator fun remAssign(other: Point){
        x %= other.x
        y %= other.y
    }

    override fun toString(): String {
        return "point[$x, $y]"
    }
}

operator fun Point.inc():Point{
    return Point(x+1, y+1)
}

operator fun Point.unaryMinus():Point{
    return Point(-x, -y)
}

operator fun Point.unaryPlus():Point{
    return Point(+x, +y)
}

operator fun Point.compareTo(other: Point):Int{
    return 0
}

operator fun Point.get(index:Int):Int{
    return when(index){
        0 -> x
        1 -> y
        else -> throw IllegalArgumentException("index $index")
    }
}

operator fun Point.get(propertyName: String):Int{
    return when(propertyName){
        "x" -> x
        "y" -> y
        else -> throw IllegalArgumentException("propertyName $propertyName")
    }
}

operator fun Point.set(index:Int, value:Int){
    when(index){
        0 -> x = value
        1 -> y = value
        else -> throw IllegalArgumentException("index $index")
    }
}

operator fun Point.set(propertyName:String, value:Int){
    when(propertyName){
        "x" -> x = value
        "y" -> y = value
        else -> throw IllegalArgumentException("propertyName $propertyName")
    }
}
