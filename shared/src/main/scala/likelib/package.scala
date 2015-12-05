package object likelib {
  import scala.language.experimental.macros

  type LongAs[A] = As[Long,A]

  type StringAs[A] = As[String,A]

  type IntAs[A] = As[Int,A]

  type LongLike[A] = Like[Long,A]

  type StringLike[A] = Like[String,A]

  type IntLike[A] = Like[Int,A]

  type StringTryLike[From] = TryLike[String,From]

  type LongTryLike[From] = TryLike[Long,From]

  type IntTryLike[From] = TryLike[Long,From]

  type StringSafeLike[From] = SafeLike[String,From]
  
  type LongSafeLike[From] = SafeLike[Long,From]

  type IntSafeLike[From] = SafeLike[Long,From]

  def like[To,From]: Like[To,From] = macro likelib.macros.Macros.like[To,From]
}
