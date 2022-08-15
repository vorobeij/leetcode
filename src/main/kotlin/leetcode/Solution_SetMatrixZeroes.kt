package leetcode

class Solution_SetMatrixZeroes {

    fun print(matrix: Array<IntArray>): String {
        return "[${matrix.joinToString(",") { "[${it.joinToString(",")}]" }}]"
    }

    data class Point(
        val i: Int,
        val j: Int
    )

    class Matrix(
        private val m: Array<IntArray>
    ) {

        fun get(i: Int, j: Int): Int {
            return m[j][i]
        }

        fun set(i: Int, j: Int, value: Int) {
            m[j][i] = value
        }

        fun getZeroes(): List<Point> {
            val res = mutableListOf<Point>()
            for (i in 0 until m[0].size) {
                for (j in 0 until m.size) {
                    if (get(i, j) == 0) {
                        res.add(Point(i, j))
                    }
                }
            }
            return res
        }

        fun zeroColumn(i: Int) {
            for (j in 0 until m.size) {
                set(i, j, 0)
            }
        }

        fun zeroRow(j: Int) {
            for (i in 0 until m[j].size) {
                set(i, j, 0)
            }
        }

        fun setZeroes(points: List<Point>) {
            val columns = points.map { it.i }.distinct()
            val rows = points.map { it.j }.distinct()
            columns.forEach { zeroColumn(it) }
            rows.forEach { zeroRow(it) }
        }
    }

    fun setZeroes(matrix: Array<IntArray>) {
        val m = Matrix(matrix)
        val points = m.getZeroes()
        m.setZeroes(points)

        println(print(matrix))
    }
}
