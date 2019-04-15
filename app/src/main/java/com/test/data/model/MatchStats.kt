package com.test.data.model

import java.io.Serializable

data class MatchStats(val match_id: String, val team_A: Team, val team_B: Team, val stat_type: String): Serializable