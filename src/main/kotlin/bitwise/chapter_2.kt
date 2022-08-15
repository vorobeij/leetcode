package bitwise

class RightmostBits {

    fun isPower2(x: Int): Int {
        println(
            "$x: ${Integer.toBinaryString(x)} & ${Integer.toBinaryString(x - 1)} = ${
                Integer.toBinaryString(
                    x and x - 1
                )
            }"
        )
        return x and x - 1
    }

    fun turnOnRightmostBit(x: Int) {
        val res = x or (x + 1)
        println("$x: ${Integer.toBinaryString(x)} | ${Integer.toBinaryString(x + 1)} = ${Integer.toBinaryString(res)}")
    }

    fun turnOffTrailingBit(x: Int) {
        val res = x and (x + 1)
        println("$x: ${Integer.toBinaryString(x)} | ${Integer.toBinaryString(x + 1)} = ${Integer.toBinaryString(res)}")
    }

    fun turnOnTrailingBit(x: Int) {
        val res = x or (x - 1)
        println("$x: ${Integer.toBinaryString(x)} | ${Integer.toBinaryString(x - 1)} = ${Integer.toBinaryString(res)}")
    }
}
