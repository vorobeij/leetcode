package leetcode.contest

class Solution_bestTeamScore {
    data class Player(
        val score: Int,
        val age: Int
    )

    fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
        val size = scores.size

        val players = mutableListOf<Player>()
        for (i in scores.indices) {
            players.add(Player(scores[i], ages[i]))
        }
        val p = players.sortedBy { it.score }.sortedBy { it.age }

        println(p.joinToString())

        var teams = mutableListOf<Int>()

        for (i in 0 until size - 1) {
            val team = mutableListOf(p[i])
            for (j in i + 1 until size) {
                if (canBeInTeam(team, p[j])) {
                    team.add(p[j])
                }
            }
            teams.add(team.sumBy { it.score })
        }

        println(teams.joinToString())

        return 0
    }

    fun canBeInTeam(team: List<Player>, player: Player): Boolean {
        return team.none { it.age < player.age && it.score > player.score }
    }
}
