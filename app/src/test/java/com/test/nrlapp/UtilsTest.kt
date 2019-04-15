package com.test.nrlapp

import com.test.data.model.MatchStats
import com.test.data.model.Player
import com.test.data.model.Team
import com.test.utils.normalizeData
import org.junit.Assert
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsTest {

    @Test
    fun normalizeDataTest() {
        val playerA1 = Player(100, "Forward", "Peter Wallace", "P. Wallace", 10.89f, 1, 123)
        val teamAPlayers = listOf(playerA1)

        val teamA = Team(123, "Penrith", "Pen", "Panthers", teamAPlayers)

        val playerB2 = Player(201, "Hooker", "Isaah Yeo", "I. Yeo", 10.89f, 2, 123)
        val teamBPlayers = listOf(playerB2)

        val teamB = Team(123, "Canteburry", "Can", "Bulldogs", teamBPlayers)

        val matchStats = MatchStats("MATCH_X", teamA, teamB, "run")

        //call the method
        val result = normalizeData(listOf(matchStats))

        Assert.assertNotNull(result)

        Assert.assertEquals(result.size, 2)

        Assert.assertEquals(result[0].statType, "run")
    }
}
