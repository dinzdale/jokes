package com.gmjproductions.dependencyinjectiontest.model

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
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

    init {
        jokeListLD.value = emptyList()
    }


//    fun fetchAllJokes() {
//        val db = JokesDatabase.getInstance()
//        jokeListLD.value = db!!.jokesDao().getAllJokes()
//    }

}
