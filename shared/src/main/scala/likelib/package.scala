package object likelib {
  //as
  type LongAs[A] = As[Long,A]
  type StringAs[A] = As[String,A]
  type IntAs[A] = As[Int,A]
  type FloatAs[A] = As[Float,A]
  type DoubleAs[A] = As[Double,A]

  //likes
  type LongLike[A] = Like[Long,A]
  type StringLike[A] = Like[String,A]
  type IntLike[A] = Like[Int,A]
  type FloatLike[A] = Like[Float,A]
  type DoubleLike[A] = Like[Double,A]

  //tries
  type LongTryLike[From] = TryLike[Long,From]
  type StringTryLike[From] = TryLike[String,From]
  type IntTryLike[From] = TryLike[Int,From]
  type FloatTryLike[A] = TryLike[Float,A]
  type DoubleTryLike[A] = TryLike[Double,A]

  //safes
  type LongSafeLike[From] = SafeLike[Long,From]
  type StringSafeLike[From] = SafeLike[String,From]
  type IntSafeLike[From] = SafeLike[Int,From]
  type FloatSafeLike[A] = SafeLike[Float,A]
  type DoubleSafeLike[A] = SafeLike[Double,A]

  //macros
  import scala.language.experimental.macros
  def like[To,From]: Like[To,From] = macro likelib.macros.Macros.like[To,From]
  def validate[To,From](make: To => scala.util.Try[From]): TryLike[To,From] = macro likelib.macros.Macros.validate[To,From]
}
