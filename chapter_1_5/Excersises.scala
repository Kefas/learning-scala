//1.

val max = (a:Int, b:Int) => if (a>b) a else b

def max3(a: Int, b:Int, c:Int, f: (Int, Int) => Int): Int = f(f(a, b), c)

max3(1, 2, 3, max)
max3(3, 2, 1, max)  


//2.

val x = util.Random.nextInt
val y = util.Random.nextInt

val invoke = (a: Int, b:Int, f: (Int, Int) => Int) => f(a,b)

invoke(x,y,max)
invoke(x,y, (x,y) => if (x>y) y else x)
invoke(x,y, (_,y) => y)

//3.

def fun(y: Int):Int=>Int = {
	(x: Int) => x + y
}

fun(2)(2)

//4.
def fzero[A](x: A)(f: A => Unit): A = { f(x); x}

fzero(2)(println(_))

//5.

def square(m: Double) = m * m
val sq = square _

def myFun(x: Double, f: Double => Double):Double = {
	f(x)
	println(x)
	f(f(x))
}

myFun(2, square)

//6. 

def conditional[A](x:A, p: A => Boolean, f: A=>A):A = if (p(x)) f(x) else x

//7.



for(i <- 1 to 100) {
	conditional[Int](i, y => y % 3 == 0 || y % 5 == 0 , x => {
		if(x % 3 == 0) {
		if (x % 5 == 0 ) {
				println("typesafe")
			} else {
				println("type")
			}
		} else {
			if (x % 5 == 0 ) {
				println("safe")
			} else {
				println(x)
			}
		}
		x
	})
}
