package leetcode.backtrack

fun main() {
    SolutionSudoku().solveSudoku(
        arrayOf(
            charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
            charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
            charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
            charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
            charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
            charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
            charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
            charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
            charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
        )
    )
}

class SolutionSudoku {
    val SIZE = 9
    val EMPTY_CELL = '.'
    fun solveSudoku(board: Array<CharArray>) {
        backtrack(board)
        output(board)
    }

    private fun nextCell(board: Array<CharArray>): Cell? {
        for (row in 0 until SIZE) {
            for (col in 0 until SIZE) {
                if (board[row][col] == EMPTY_CELL) return Cell(row, col)
            }
        }
        return null
    }

    data class Cell(
        val row: Int,
        val col: Int
    )

    private fun backtrack(board: Array<CharArray>): Boolean {
        val nextCell = nextCell(board)
        if (isSolution(board)) {
            return true
        }
        nextCell!!

        for (nextCandidate in 1..SIZE) {
            if (isValid(board, nextCell, nextCandidate)) {
                board[nextCell.row][nextCell.col] = nextCandidate.char() // put

                if (backtrack(board)) {
                    return true
                }

                board[nextCell.row][nextCell.col] = EMPTY_CELL // remove
            }
        }

        return false
    }

    private fun isValid(board: Array<CharArray>, cell: Cell, candidate: Int): Boolean {
        val c = candidate.char()
        // column
        for (column in 0 until SIZE) {
            if (column != cell.col) {
                if (board[cell.row][column] == c) return false
            }
        }
        // row
        for (roww in 0 until SIZE) {
            if (roww != cell.row) {
                if (board[roww][cell.col] == c) return false
            }
        }
        // todo boxes
        val cellStart = Cell(cell.row / 3 * 3, cell.col / 3 * 3)
        val cellEnd = Cell((cell.row / 3 + 1) * 3 - 1, (cell.col / 3 + 1) * 3 - 1)
        for (row in cellStart.row..cellEnd.row) {
            for (col in cellStart.col..cellEnd.col) {
                if (board[row][col] == c) return false
            }
        }
        return true
    }

    private fun Int.char() = toString().toCharArray().first()

    private fun isSolution(board: Array<CharArray>): Boolean {
        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                if (board[r][c] == EMPTY_CELL) return false
            }
        }
        return true
    }

    private fun output(board: Array<CharArray>) {
        //        println("[${board.toList().joinToString() { "[${it.joinToString(",") { "\"${it}\"" }}]" }}]")
        println("${board.toList().joinToString("\n") { "[${it.joinToString(" ")}]" }}\n\n")
    }
}
