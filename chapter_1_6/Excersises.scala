//1.

for(i <- 0 to 19) yield { i * 2 + 1}

for(i <- 0 to 40 if i % 2 != 0) yield i

val list = for(i <- 0 to 19) yield i
list map { _ * 2 + 1}

//2.

def factors(number: Int): List[Int] = {
  val arr = for(i <- 2 until number if number % i == 0) yield i
  arr.toList
}

factors(15)

list flatMap  { x => factors(x)}

val list2 = List(9, 11, 13, 15)
list2 flatMap { x => factors(x)}

//3.

//def first[A](items: List[A], count: Int): List[A] = items take count

//def first[A](items: List[A], count: Int): List[A] = {
//  val arr = for(i <- 0 until {if (count > items.size) items.size else count} ) yield items(i)
//  arr.toList
//}

//def first[A](items: List[A], count: Int): List[A] = {
//  items.foldLeft(List[A]()) {
//    (a :List[A], b: A) => {
//      if (a.size < count)
//        b :: a
//      else
//        a
//    }
//  }.reverse
//}

@annotation.tailrec
def recursive[A](items: List[A], count: Int, a: List[A]): List[A] = {
  if (count > 0 && items.size > 0)
    recursive(items.tail, count - 1, items.head :: a)
  else a.reverse
}

def first[A](items: List[A], count: Int): List[A] = {
  recursive(items, count, List())
}

first(List(1, 2, 3), 2)

//4.

val longerString = { (a: String,b :String) => if (b.size > a.size) b else a }

def funFold(strings: List[String]): String = {
  strings.foldLeft("")(longerString(_,_))
}

funFold(List("1234", "123456", "123"))


def funReduce(strings: List[String]): String = {
  strings.reduce(longerString(_,_))
}

funReduce(List("1234", "123456", "123"))

//5.

def myReverseRec[A](list: List[A], a: List[A]): List[Any] = list match {
  case x :: xs => myReverseRec(xs, x :: a)
  case Nil => a
}

def myReverse[A](list: List[A]): List[Any] = myReverseRec(list, List())

myReverse(List(1,2,3))

//6.

def fun6(strings: List[String]): (List[String], List[String]) = {
  strings.partition(s => s.reverse == s )
}

fun6(List("racecar", "race", "kayak", "hotel"))

//7.

val url: String = "http://api.openweathermap.org/data/2.5/forecast?mode=xml&lat=20&lon=50"
val l: List[String] = io.Source.fromURL(url).getLines.toList
println(l(0))
