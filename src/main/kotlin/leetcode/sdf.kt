class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(n) { _ -> Array(m) { j -> 0 } }
        for(y in 0 until n) dp[y][m-1] = 1
        for(x in 0 until m) dp[n-1][x] = 1

        for(x in m-2 downTo 0){
            for(y in n-2 downTo 0){
                dp[y][x] = dp[x+1][y]+dp[x][y+1]
            }
        }

        return dp[0][0]
    }
}