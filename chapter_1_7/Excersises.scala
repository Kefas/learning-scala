import java.io.File

import scala.collection.mutable
import scala.concurrent.Future
import scala.util.Try

//1.

//a

def fibonacci(x: Int): List[Long] = {
  val buf = mutable.ArrayBuffer[Long](1,1)
  for(i <- 1 until x-1) buf += buf(i) + buf(i-1)
  buf.toList
}

val listFib = fibonacci(10)

//b

//def fibonacci(list: List[Long], x: Long): List[Long] = {
//  if (list.size > x)
//    list take x
//  else {
//    val buf = list.toBuffer
//    for(i <- buf.size-1 to x) buf += buf(i) + buf(i-1)
//    buf.toList
//  }
//}

def fibonacci(list: List[Long], x: Int): List[Long] = {
  if (list.lengthCompare(x) >= 0)
    list takeRight x
  else
    fibonacci(list.take(2).sum :: list, x)
}

fibonacci(listFib.reverse, 15).reverse


//c
def fibStream(x: Long, y: Long): Stream[Long] = Stream.cons(y, fibStream(y, x+y))

val s = fibStream(1,1)

val list = s.take(100).toList
list.size

for(i <- 0 until list.size) if ( i > 0 && i % 10 == 0) println(list(i)) else print(list(i) + ", ")


//d

def fibNext(x: Int): Option[Long] = {
  val s = fibStream(1,1)
  val fibList = s.take(x+1).toList
  val index = fibList.indexOf(x)
  if(index > 0)
    Option(fibList(index + 1))
  else
    None

}


fibNext(8)
fibNext(9)


//2.

def ls(path: String): Array[File] = {
  new java.io.File(path).listFiles
}

val files = ls("/users/sg0222871")

for (file <- files.filter( !_.getName().startsWith("."))) print(file.getName() + "; ")


//3.

val names = files.map(_.getName()).toList.sortWith(_ < _)
for(c <- 'a' to 'z') {
  val temp = names.filter(s => s.toLowerCase.startsWith(c.toChar.toString))
  println(c.toChar + " " + temp.size)
}

//4.

def product(a: String, b: String): Double = {
  Try(a.toDouble).getOrElse(0.0) * Try(b.toDouble).getOrElse(0.0)
}

product("1.0", "2.0")
product("a", "2,0")

//5.

System.getProperties

def getProperty(prop: String): String = {
  util.Try(System.getProperty(prop)).getOrElse("")
}

getProperty("java.home")
getProperty("blah")

//6.


val settings = List(("scala", "scala", "2.13.x.atom"))

def gitCommits(settings: (String, String, String)) = {
  val u = s"https://github.com/${settings._1}/${settings._2}/commits/${settings._3}"
  val str = io.Source.fromURL(u)
  val text = str.getLines.map(_.trim).mkString("")
  val entries = text.split("<entry>").filter(s => s.endsWith("</entry>"))
  println(u)
  for(entry <- entries) {
    println(entry)
  }
}






