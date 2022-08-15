package leetcode

class Solution_exist {

    fun exist(board: Array<CharArray>, word: String): Boolean {
        for (y in board.indices) {
            for (x in board[0].indices) {
                if (BFS(board).bfs(word, 0, x, y)) return true
            }
        }

        return false
    }

    class BFS(
        private val board: Array<CharArray>
    ) {

        data class Direction(
            val x: Int,
            val y: Int
        )

        val directions = listOf(
            Direction(0, 1),
            Direction(0, -1),
            Direction(1, 0),
            Direction(-1, 0)
        )

        fun bfs(
            word: String,
            wordIndex: Int,
            x: Int,
            y: Int
        ): Boolean {
            if (wordIndex >= word.length) return true
            if (y !in board.indices || x !in board[0].indices || board[y][x] == '0' || word[wordIndex] != board[y][x]) {
                return false
            }
            val cell = board[y][x]
            board[y][x] = '0'
            for (it in directions) {
                if (bfs(word, wordIndex + 1, x + it.x, y + it.y)) return true
            }
            board[y][x] = cell
            return false
        }
    }
}
