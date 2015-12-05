package likelib

import utest._

object LikelibTest extends TestSuite {

  case class UserId(id: Long) extends AnyVal

  case class Email(value: String) extends AnyVal

  def tests = TestSuite {
    'likeMacroLong {
      val uidLike = likelib.like[Long,UserId]
      assert(uidLike.from(42) == UserId(42))
      assert(uidLike.to(UserId(100)) == 100l)
    }
    'likeMacroString {
      val emailLike = likelib.like[String,Email]
      assert(emailLike.from("foo@bar.com") == Email("foo@bar.com"))
      assert(emailLike.to(Email("foo@bar.com")) == "foo@bar.com")
    }
  }
}
