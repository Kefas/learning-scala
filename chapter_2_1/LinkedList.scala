abstract class AbstractNode[A] {
  def foreach(f: A => Unit): Unit
  def apply(index: Int): Option[A]
  def headOption(): Option[A] = apply(0)
  lazy val head: Option[A] = headOption()
  def tail: AbstractNode[A]

  lazy val size: Int = {
    var result = 0
    foreach { _ => result += 1 }
    result
  }
  def filter(f: A => Boolean): AbstractNode[A] = {
    var result: AbstractNode[A] = new EmptyNode[A]
    foreach {
      x => if(f(x)) result = new Node[A](x, result)
    }
    result.reverse
  }
  def map[B](f: A => B): AbstractNode[B] = {
    var result: AbstractNode[B] = new EmptyNode[B]
    foreach {
      x => result = new Node[B](f(x), result)
    }
    result.reverse
  }

  def reverse(): AbstractNode[A] = {
    var result: AbstractNode[A] = new EmptyNode[A]
    foreach { x => result = new Node[A](x, result) }
    result
  }
}

class Node[A](val item: A, val tail: AbstractNode[A]) extends AbstractNode[A] {
  override def foreach(f: A => Unit): Unit = {
    f(item)
    tail.foreach(f)
  }

  override def apply(index: Int): Option[A] = {
    if(index == 0) Some(item) else tail(index-1)
  }
}


class EmptyNode[A] extends AbstractNode[A] {
  override def foreach(f: A => Unit): Unit = {}
  override def apply(index: Int): Option[A] = None
  override def tail: AbstractNode[A] = null

}

class LinkedList {
  def create[A](items: A*) = {
    var result: AbstractNode[A] = new EmptyNode[A]
    for (item <- items.reverse) {
      result = new Node[A](item, result)
    }
    result
  }
}

val fac = new LinkedList
val ll = fac.create(1,2,3)

