package com.gmjproductions.dependencyinjectiontest.model

import android.arch.lifecycle.*
import android.content.Context
import com.gmjproductions.dependencyinjectiontest.database.JokesDatabase
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import com.gmjproductions.dependencyinjectiontest.ui.MyApplication

import javax.inject.Inject

/**
 * Created by garyjacobs on 1/26/18.
 */


class JokesViewModelFactory @Inject constructor() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokesViewModel() as T
    }
}

class JokesViewModel() : ViewModel() {
    var jokeListLD = MutableLiveData<List<Joke>>()
    var jokeTypesLD = MutableLiveData<List<JokeType>>()

    init {
        jokeListLD.value = emptyList()
        jokeTypesLD.value = emptyList()
    }

}
