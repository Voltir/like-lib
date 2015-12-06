package likelib

trait As[To,From] {
  def to(inp: From): To
}

trait Like[To,From] extends As[To,From] {
  def from(inp: To): From
}

trait TryLike[To,From] extends As[To,From] { self =>
  def from(inp: To): scala.util.Try[From]
  def withDefault(d: From): SafeLike[To,From] = new SafeLike[To,From] {
    override val default=d
    override def from(inp: To): scala.util.Try[From] = self.from(inp)
    override def to(inp: From): To = self.to(inp)
  }
}

trait SafeLike[To,From] extends TryLike[To,From] {
  def default: From
  def fromSafe(inp: To) = from(inp).getOrElse(default)
}