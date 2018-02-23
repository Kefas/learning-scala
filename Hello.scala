// 1.

val str:String = "Hello, World!"
str match {
	case s if s.size > 0 => println(s)
	case s if s.size == 0 => println("n/a")
}

//2.

val amount:Double = 1.0

//if else
if (amount > 0) println("greater") else {
	if(amount == 0) println("same") else println("less")
}

//pattern matching

amount match {
	case a if a > 0 => println("greater")
	case a if a < 0 => println("less")
	case other => println("same")
}

//3.

val color:String = "cyan"

val converted:Int = color match {
	case "cyan" => 0x00FFFF
	case "magenta" => 0xFF00FF
	case "red" => 0xFFFF00
	case other => 0
}

println(converted)

//4.

for(x <- 0 to 19) {
	for(y <- 1 to 5) {
		print(x * 5 + y + " ")
	}
	println
}

//5.

for(arg <- 1 to 100) arg match {
	case x if x % 3 == 0 && x % 5 == 0 => println("typesafe") 
	case x if x % 3 == 0 => println("type") 
	case x if x % 5 == 0 => println("safe")
	case other => println(other)
}

//6. 

for(x <- 1 to 100) if (x%3==0) { if (x%5==0) println("typesafe"); else println("type")} else { if (x%5==0) println("safe"); else println(x)}

