package com.test.data.net

class URL {
    companion object {
        const val BASE_URL = "https://statsapi.foxsports.com.au/3.0/"
        const val API_KEY = "A00239D3-45F6-4A0A-810C-54A347F144C2"
        const val PLAYER_STATS_URL = "api/sports/league/matches/NRL20172101/topplayerstats.json;type=fantasy_points;type=tackles;type=runs;type=run_metres?limit=5&userkey=$API_KEY"
        const val PLAYER_DETAILS_URL = "api/sports/league/series/1/seasons/115/teams/{team_id}/players/{player_id}/detailedstats.json?userkey=$API_KEY"
        const val PLAYER_IMAGE_URL = "- http://media.foxsports.com.au/matchcentre/includes/images/headshots/nrl/"
    }
}