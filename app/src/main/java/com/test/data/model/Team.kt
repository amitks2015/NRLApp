package com.test.data.model

import java.io.Serializable

data class Team(val id: Long,
                val name: String,
                val code: String,
                val short_name: String,
                val top_players: List<Player>?): Serializable