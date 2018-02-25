package com.gmjproductions.dependencyinjectiontest.model2

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gmjproductions.dependencyinjectiontest.MainActivity
import com.gmjproductions.dependencyinjectiontest.network.APIRepository

import java.util.ArrayList

import javax.inject.Inject

/**
 * Created by garyjacobs on 1/26/18.
 */


data class JokesViewModelFactory @Inject constructor(var apiRepository: APIRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokesViewModel(apiRepository) as T
    }
}

data class JokesViewModel(val apiRepository: APIRepository) : ViewModel() {
    var jokeListLD = MutableLiveData<List<Joke>>()

    init {
        jokeListLD.value = emptyList()
    }

    fun fetchJokes() {
        jokeListLD.value = apiRepository.getJokeList()
    }

}
