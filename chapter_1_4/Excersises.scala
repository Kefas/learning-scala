//1.
def circleArea(radius: Double): Double = 3.14 * radius * radius

//2.
def circleAreaString(radius: String): Double = circleArea(radius.toDouble)

//3.

@annotation.tailrec
def printFives(number: Int): Unit = { 
	if (number < 55) {
		println(number)
		printFives(number + 5)
	}
}

//4.
def msToDate(ms: Long): String = {
	(ms / 1000 / 60 / 60 / 24).floor.toInt + "d " +
	((ms / 1000 / 60 / 60) % 24) + "h " +
	((ms / 1000 / 60) % 60) + "m " + 
	((ms / 1000) % 60) + "s"
}

//5.

// def myExp(x: Double, y: Int): Double = scala.math.pow(x, y)
def myExp(x: Double, y: Int): Double = {
	var res: Double = x
	if (y == 0) res = 1
	for(i <- 1 to y-1) res = res * x
	res
}

//6.
def pointDiff(x1: Int, y1: Int)(x2:Int , y2: Int): (Int, Int) = (x2-x1, y2-y1)

//7.

//8.
def ex8[A,B,C](x: A, y: B, z: C): (A, String, B, String, C, String) = {
	(x, x.toString, y, y.toString, z, z.toString)
}