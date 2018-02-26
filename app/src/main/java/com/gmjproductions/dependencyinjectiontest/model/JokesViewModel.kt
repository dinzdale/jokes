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


data class JokesViewModelFactory @Inject constructor(val context: Context, val apiRepository: APIRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokesViewModel(context, apiRepository) as T
    }
}

data class JokesViewModel(val context: Context, val apiRepository: APIRepository) : ViewModel() {
    var jokeListLD = MutableLiveData<List<Joke>>()

    init {
        jokeListLD.value = emptyList()
    }

    //TODO this should be abstracted out of the viewmodel
    fun fetchNewJokes() = apiRepository.loadNewJokeList(context)


//    fun fetchAllJokes() {
//        val db = JokesDatabase.getInstance()
//        jokeListLD.value = db!!.jokesDao().getAllJokes()
//    }

}
