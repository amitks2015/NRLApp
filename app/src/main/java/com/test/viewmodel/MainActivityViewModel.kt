package com.test.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.test.data.model.MatchStats
import com.test.data.net.RetrofitService
import com.test.utils.Response
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class MainActivityViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    val matchStats = MutableLiveData<Response<List<MatchStats>>>()

    fun getMatchStats() {
        disposable.add(RetrofitService.getEndpoint().getTopPlayers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ matchStats.setValue(Response.loading()) }
            .subscribe(
                { result -> matchStats.setValue(Response.success(result)) },
                { throwable -> matchStats.setValue(Response.error(throwable)) }
            ))

    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}