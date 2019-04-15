package com.test.nrlapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.data.model.PlayerDetails
import com.test.data.net.URL
import com.test.utils.ARG_PLAYER_ID
import com.test.utils.ARG_TEAM_ID
import com.test.utils.Response
import com.test.utils.Status
import com.test.viewmodel.PlayerDetailsViewModel
import kotlinx.android.synthetic.main.activity_player_details.*

class PlayerDetailsActivity: AppCompatActivity() {

    lateinit var viewModel: PlayerDetailsViewModel

    private var teamId = -1L
    private var playerId = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details)
        teamId = intent.getLongExtra(ARG_TEAM_ID, teamId)
        playerId = intent.getLongExtra(ARG_PLAYER_ID, playerId)
        viewModel = ViewModelProviders.of(this).get(PlayerDetailsViewModel::class.java)

        val observer =  Observer<Response<PlayerDetails>> {
            it?.let {
                handleApiResponse(it)
                Toast.makeText(this, it.status.name, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.playerDetails.observe(this, observer)
    }

    override fun onResume() {
        super.onResume()
        if(playerId != -1L && teamId != -1L)
        viewModel.getPlayerDetails(teamId, playerId)
    }

    private fun handleApiResponse(response: Response<PlayerDetails>) {
        when(response.status) {
            Status.ERROR -> {
                //Handle Error
            }

            Status.LOADING -> {
                //show progress
            }

            Status.SUCCESS -> {
                response.data ?.let {playerDetails ->
                    updateUI(playerDetails)
                }
            }

        }

    }

    private fun updateUI(playerDetails: PlayerDetails) {
        with(playerDetails) {
            Glide.with(ivPlayerImage).load(URL.PLAYER_IMAGE_URL+id+".jpg")
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_default_headshot)
                .into(ivPlayerImage)
            tvPlayerFullName.text = full_name
            tvPlayerPosition.text = position
            tvLastMatchStats.text = last_match_stats.toString()
        }
    }
}