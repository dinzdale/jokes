package com.gmjproductions.dependencyinjectiontest.model2

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import java.util.ArrayList

import javax.inject.Inject

/**
 * Created by garyjacobs on 1/26/18.
 */

class JokesViewModel @Inject constructor() : ViewModel() {

    internal var jokeListLD = MutableLiveData<List<Joke>>()

    init {
        jokeListLD.value = ArrayList()
    }

    fun getJokeListLD(): LiveData<List<Joke>> {
        return jokeListLD
    }

    fun setJokeList(jokeList: List<Joke>) {
        this.jokeListLD.value = jokeList
    }
}
