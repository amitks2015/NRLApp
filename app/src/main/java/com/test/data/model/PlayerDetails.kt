package com.test.data.model

import java.io.Serializable

data class PlayerDetails(val id: Long, val full_name: String, val position: String, val last_match_stats: LastMatchStats): Serializable