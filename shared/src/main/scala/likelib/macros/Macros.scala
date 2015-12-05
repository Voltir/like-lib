package likelib.macros


import likelib._

import scala.reflect.macros._
import scala.language.experimental.macros

object Macros {

  def getCompanion(c: blackbox.Context)(tpe: c.Type) = {
    import c.universe._
    val symTab = c.universe.asInstanceOf[reflect.internal.SymbolTable]
    val pre = tpe.asInstanceOf[symTab.Type].prefix.asInstanceOf[Type]
    c.universe.internal.gen.mkAttributedRef(pre, tpe.typeSymbol.companion)
  }

  def like[To: c.WeakTypeTag, From: c.WeakTypeTag](c: blackbox.Context): c.Expr[Like[To,From]] = {
    import c.universe._

    val ToTpe = weakTypeTag[To].tpe
    val FromTpe = weakTypeTag[From].tpe


    val isCaseClass = FromTpe.typeSymbol.asClass.isCaseClass && !ToTpe.typeSymbol.asClass.isModuleClass

    if(!isCaseClass) {
      c.abort(c.enclosingPosition,s"Can't generate Like TypeClass for $FromTpe, it must be a case class!")
    }

    val accessors = FromTpe.decls.filter(_.asTerm.isAccessor)

    if(accessors.size != 1) {
      c.abort(c.enclosingPosition,s"$FromTpe must extends AnyVal!")
    }

    val companion = getCompanion(c)(FromTpe)

    val wat = q"""
       new Like[$ToTpe,$FromTpe] {
         override def to(inp: $FromTpe): $ToTpe = { inp.${accessors.head.asTerm} }
         override def from(inp: $ToTpe): $FromTpe = { $companion.apply(inp) }
       }
     """

    c.Expr(wat)
  }
}
