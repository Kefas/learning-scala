import org.scalatest._

class SafeStringUtilsSpec extends FlatSpec with Matchers {
  "The Safe String Utils object" should "remove trailing spaces from beginning and end" in {
    SafeStringUtils.trimToNone("  Hello  ") should equal(Some("Hello"))
  }

  it should "return None on empty string" in {
    val emptyString = ""
    SafeStringUtils.trimToNone(emptyString) should equal(None)
  }

  it should "return None on Null" in {
    val emptyString = null
    SafeStringUtils.trimToNone(emptyString) should equal(None)
  }

  it should "return Some(Int) when string can be parsed" in {
    SafeStringUtils.stringToInt("1") should equal(Some(1))
    SafeStringUtils.stringToInt(" 1 ") should equal(Some(1))
  }

  it should "return None when string can not be parsed to Int" in {
    val string = "test"
    SafeStringUtils.stringToInt("test") should equal(None)
  }

  it should "return Some(Long) when string can be parsed" in {
    SafeStringUtils.stringToLong("1") should equal(Some(1L))
    SafeStringUtils.stringToLong(" 1 ") should equal(Some(1L))
  }

  it should "return None when string can not be parsed to Long" in {
    val string = "test"
    SafeStringUtils.stringToLong("test") should equal(None)
  }

  it should "generate string on given size" in {
    SafeStringUtils.generateString(8).length should equal(8)
    SafeStringUtils.generateString(20).length should equal(20)
  }

  it should "contain only lower and upper cases" in {
    SafeStringUtils.generateString(10).count(x => x.isUpper || x.isLower) should equal(10)
  }
}