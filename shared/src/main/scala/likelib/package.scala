package object likelib {
  type LongLike[A] = Like[Long,A]

  type StringLike[A] = Like[String,A]

  type IntLike[A] = Like[Int,A]

  type StringTryLike[From] = TryLike[String,From]

  type LongTryLike[From] = TryLike[Long,From]

  type IntTryLike[From] = TryLike[Long,From]

  type StringSafeLike[From] = SafeLike[String,From]
  
  type LongSafeLike[From] = SafeLike[Long,From]

  type IntSafeLike[From] = SafeLike[Long,From]
}
