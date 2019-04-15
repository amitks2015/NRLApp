package com.test.utils

import com.test.data.model.ListItem
import com.test.data.model.MatchStats

fun normalizeData(data: List<MatchStats>): List<ListItem> {
    val items = ArrayList<ListItem>()
    for(matchStats in data) {
        val item = ListItem(matchStats.stat_type, null, null)
        items.add(item)
        val playersA = matchStats.team_A.top_players
        val playersB = matchStats.team_B.top_players
        val subListItems = playersA?.mapIndexed { index, playerA ->
            playerA.teamId = matchStats.team_A.id
            val playerB = playersB?.get(index)
            playerB?.teamId = matchStats.team_B.id
            ListItem(playerA = playerA, playerB = playerB)
        }
        subListItems?.let {
            items.addAll(it)
        }
    }
    return items
}