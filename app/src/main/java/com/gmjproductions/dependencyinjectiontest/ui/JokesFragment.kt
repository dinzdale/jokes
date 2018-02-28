package com.gmjproductions.dependencyinjectiontest.ui

import android.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gmjproductions.dependencyinjectiontest.R
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokeType
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModel
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModelFactory
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.jokes_fragment_layout.*
import java.lang.Boolean.getBoolean
import javax.inject.Inject


/**
 * Created by garyjacobs on 2/26/18.
 */
class JokesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: JokesViewModelFactory

    lateinit var viewModel: JokesViewModel

    @Inject
    lateinit var apiRepository: APIRepository

    lateinit var myActivity: FragmentActivity

    val allJokeType = JokeType(ALL)
    val lastJokeType = JokeType(ALL)

    var configChanged = false

    companion object {
        val ALL = "ALL"
        val CONFIGCHANGED = "CONFIGCHANGED"
        val SELECTEDTYPE = "SELECTEDTYPE"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.jokes_fragment_layout, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
        configChanged = savedInstanceState?.let {
            it.getBoolean(CONFIGCHANGED)
        } ?: false

        if (configChanged) {
            savedInstanceState?.getString(SELECTEDTYPE)?.let {
                lastJokeType.type = it
            }
        }

        myActivity = activity as FragmentActivity

        viewModel = ViewModelProviders.of(myActivity, viewModelFactory).get(JokesViewModel::class.java)

        load_jokes_btn.setOnClickListener {
            apiRepository.loadNewJokeList()
            val list = apiRepository.loadAllJokeTypesFromDB().toMutableList()
            list.add(0, allJokeType)
            viewModel.jokeTypesLD.value = list
        }

        jokes_list.layoutManager = LinearLayoutManager(myActivity, LinearLayout.VERTICAL, false)
        jokes_list.adapter = JokesListAdapter(myActivity, viewModel.jokeListLD.value!!)

        joke_types_spinner.adapter = ArrayAdapter<String>(myActivity, android.R.layout.simple_spinner_dropdown_item)

        viewModel.jokeListLD.observe(myActivity, object : Observer<List<Joke>> {
            override fun onChanged(jokeList: List<Joke>?) {
                jokeList?.let {
                    val adapter = (jokes_list.adapter as JokesListAdapter)
                    adapter.list = it
                    adapter.notifyDataSetChanged()
                }
            }
        })

        viewModel.jokeTypesLD.observe(myActivity, object : Observer<List<JokeType>> {
            override fun onChanged(jokeTypeList: List<JokeType>?) {
                jokeTypeList?.let {
                    loadSpinner(it)
                }
            }
        })

        delete_all.setOnClickListener {
            apiRepository.deleteAllFromDB()
            viewModel.jokeListLD.value = apiRepository.loadAllJokesFromDB()
            viewModel.jokeTypesLD.value = apiRepository.loadAllJokeTypesFromDB()
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            if (joke_types_spinner.adapter.isEmpty) {
                it.putBoolean(CONFIGCHANGED, false)
            } else {
                it.putString(SELECTEDTYPE, (joke_types_spinner.selectedItem as JokeType).type)
                it.putBoolean(CONFIGCHANGED, true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!configChanged) {
            load_jokes_btn.performClick()
        }
        configChanged = false
    }

    val spinnerSelected = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, pos: Int, p3: Long) {
            adapterView?.let {
                val item = it.getItemAtPosition(pos) as JokeType
                if (item == allJokeType) {
                    viewModel.jokeListLD.value = apiRepository.loadAllJokesFromDB()
                } else {
                    viewModel.jokeListLD.value = apiRepository.loadAllJokesOfTypeFromDB(item)
                }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }

    private fun loadSpinner(jokeTypeList: List<JokeType>) {
        joke_types_spinner.adapter = ArrayAdapter<JokeType>(myActivity, android.R.layout.simple_spinner_dropdown_item, jokeTypeList)
        joke_types_spinner.onItemSelectedListener = spinnerSelected
        if (configChanged) {
            val pos = (joke_types_spinner.adapter as ArrayAdapter<JokeType>).getPosition(lastJokeType)
            joke_types_spinner.setSelection(pos)
        }
    }

    class JokesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val setup = itemView.findViewById<TextView>(R.id.setup)
        val punchline = itemView.findViewById<TextView>(R.id.punchline)
    }

    class JokesListAdapter(val context: Context, var list: List<Joke>) : RecyclerView.Adapter<JokesListViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JokesListViewHolder {
            return JokesListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, null))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: JokesListViewHolder, position: Int) {
            holder.setup.text = context.getString(R.string.question, list[position].setup)
            holder.punchline.text = context.getString(R.string.answer, list[position].punchline)
        }
    }


}