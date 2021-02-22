package zio.flow

trait ExprList[+A] {
  def self: Expr[A]

  final def fold[A0, B](initial: Expr[B])(f: (Expr[B], Expr[A0]) => Expr[B])(implicit ev: A <:< List[A0]): Expr[B] =
    Expr.Fold(self.widen[List[A0]], initial, (tuple: Expr[(B, A0)]) => f(tuple._1, tuple._2))

  final def length[A0](implicit ev: A <:< List[A0]): Expr[Int] =
    self.fold[A0, Int](0)((len, _) => len + 1)

  //def map[A1, B](f: Expr[A1] => Expr[B])(implicit ev1: A <:< List[A1], ev2: A <:< Mappable[A1]): Expr[List[B]] = ???
}
