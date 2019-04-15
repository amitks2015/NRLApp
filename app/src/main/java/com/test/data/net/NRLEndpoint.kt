package com.test.data.net

import com.test.data.model.MatchStats
import com.test.data.model.Player
import com.test.data.model.PlayerDetails
import com.test.data.net.URL.Companion.PLAYER_DETAILS_URL
import com.test.data.net.URL.Companion.PLAYER_STATS_URL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface NRLEndpoint {
    @GET(PLAYER_STATS_URL)
    fun getTopPlayers(): Observable<List<MatchStats>>

    @GET(PLAYER_DETAILS_URL)
    fun getPlayerDetails(@Path("team_id")teamId: Long,  @Path ("player_id") playerId: Long): Observable<PlayerDetails>
}