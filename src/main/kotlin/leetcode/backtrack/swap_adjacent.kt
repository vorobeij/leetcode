package leetcode.backtrack


class SolutionPow {
    fun myPow(x: Double, n: Int): Double {
        var x = x
        var ans = 1.0
        var power = n.toLong()
        if (n < 0) {
            power *= -1
        }
        while (power > 0) {
            if (power % 2 == 0L) {
                x *= x
                power /= 2
            } else {
                ans *= x
                power -= 1
            }
        }

        //for negative power
        return if (n < 0) 1.0 / ans else ans
    }
}

class SolutionPow2 {
    fun myPow(x: Double, n: Int): Double {
        var x = x
        var ans = 1.0
        var power = n
        if (n < 0) {
            power = -power
        }
        while (power > 0) {
            if (power % 2 == 0) {
                x *= x
                power /= 2
            } else {
                ans *= x
                power -= 1
            }
        }
        return if (n < 0) 1.0 / ans else ans
    }
}