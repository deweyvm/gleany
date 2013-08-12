package com.explatcreations.gleany

object GleanyMath {
    def clamp[T](value:T, min:T, max:T)(implicit n: Numeric[T]) = {
        import n.{lt,gt}
        if (lt(value, min)) {
            min
        } else if (gt(value, max)) {
            max
        } else {
            value
        }
    }
}
