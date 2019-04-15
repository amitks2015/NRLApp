package com.test.data.model

import java.io.Serializable

data class Player(val id: Long,
                  val position: String,
                  val full_name: String,
                  val short_name: String,
                  val stat_value: Float,
                  val jumper_number: Int,
                  var teamId: Long?=null): Serializable