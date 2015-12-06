package likelib

import utest._

import scala.util.{Failure, Success}

object LikelibTest extends TestSuite {

  case class UserId(id: Long) extends AnyVal

  case class Thing(value: String) extends AnyVal

  //Example of validation
  class Email private (val value: String) extends AnyVal

  object Email {
    import scala.util._
    implicit val like = likelib.validate[String,Email] { txt =>
      if(txt.contains("@")) Success(new Email(txt))
      else  Failure(new IllegalArgumentException("Requires a valid email."))
    }
  }

  def tests = TestSuite {
    'likeMacroLong {
      val like = likelib.like[Long,UserId]
      assert(like.from(42) == UserId(42))
      assert(like.to(UserId(100)) == 100l)
    }
    'likeMacroString {
      val like = likelib.like[String,Thing]
      assert(like.from("foo") == Thing("foo"))
      assert(like.to(Thing("bar")) == "bar")
    }
    'likeEmail {
      assertMatch(Email.like.from("foo@bar.com")) { case Success(e: Email) if e.value == "foo@bar.com" => }
      assertMatch(Email.like.from("not_email")) { case Failure(_) => }
      val anEmail = Email.like.from("a@a.a").get
      assert(Email.like.to(anEmail) == "a@a.a")
    }
  }
}
