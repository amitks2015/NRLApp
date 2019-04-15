package com.test.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.test.data.model.PlayerDetails
import com.test.data.net.RetrofitService
import com.test.utils.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class PlayerDetailsViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    val playerDetails = MutableLiveData<Response<PlayerDetails>>()

    fun getPlayerDetails(teamId: Long, playerId: Long) {
        disposable.add(RetrofitService.getEndpoint().getPlayerDetails(teamId, playerId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ playerDetails.setValue(Response.loading()) }
            .subscribe(
                { result -> playerDetails.setValue(Response.success(result)) },
                { throwable -> playerDetails.setValue(Response.error(throwable)) }
            ))

    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}