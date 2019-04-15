package com.test.nrlapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.test.data.model.ListItem
import com.test.data.model.MatchStats
import com.test.utils.*
import com.test.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        val observer =  Observer<Response<List<MatchStats>>> {
            it?.let {
                handleApiResponse(it)
                Toast.makeText(this, it.status.name, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.matchStats.observe(this, observer)
        viewModel.getMatchStats()
    }

    private fun handleApiResponse(response: Response<List<MatchStats>>) {
        when(response.status) {
            Status.ERROR -> {
                //Handle Error
            }

            Status.LOADING -> {
                //show progress
            }

            Status.SUCCESS -> {
                response.data ?.let {data ->
                    if(data.isNotEmpty()) {
                        val firstMatchStats = data[0]
                        val teamALabel = "${firstMatchStats.team_A.name} (${firstMatchStats.team_A.short_name})"
                        val teamBLabel = "${firstMatchStats.team_B.name} (${firstMatchStats.team_B.short_name})"
                        updateTeamLabels(teamALabel, teamBLabel)
                        val normalizedData = normalizeData(data)
                        updateUI(normalizedData)
                    }
                }
            }

        }
    }

    private fun updateUI(data: List<ListItem>) {
        val adapter = TopPlayersListAdapter(data) { teamId, playerId ->
            val intent = Intent(this, PlayerDetailsActivity::class.java)
            intent.putExtra(ARG_TEAM_ID, teamId)
            intent.putExtra(ARG_PLAYER_ID, playerId)
            startActivity(intent)
        }
        playersList.layoutManager = LinearLayoutManager(this)
        playersList.adapter = adapter
    }

    private fun updateTeamLabels(teamALabel: String, teamBLabel: String) {
        tvTeamALabel.text = teamALabel
        tvTeamBLabel.text = teamBLabel
    }
}
