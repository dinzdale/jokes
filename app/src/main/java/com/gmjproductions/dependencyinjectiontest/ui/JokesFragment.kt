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
import android.widget.LinearLayout
import android.widget.TextView
import com.gmjproductions.dependencyinjectiontest.R
import com.gmjproductions.dependencyinjectiontest.model.Joke
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModel
import com.gmjproductions.dependencyinjectiontest.model.JokesViewModelFactory
import com.gmjproductions.dependencyinjectiontest.network.APIRepository
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.jokes_fragment_layout.*
import javax.inject.Inject


/**
 * Created by garyjacobs on 2/26/18.
 */
class JokesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: JokesViewModelFactory

    lateinit var viewModel : JokesViewModel

    @Inject
    lateinit var apiRepository: APIRepository

    lateinit var myActivity: FragmentActivity


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.jokes_fragment_layout, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onViewCreated(view, savedInstanceState)
        myActivity = activity as FragmentActivity

        viewModel = ViewModelProviders.of(myActivity, viewModelFactory).get(JokesViewModel::class.java)

        load_jokes_btn.setOnClickListener {
            viewModel.jokeListLD.value = apiRepository.loadNewJokeList()
        }

        jokes_list.layoutManager = LinearLayoutManager(myActivity, LinearLayout.VERTICAL, false)
        jokes_list.adapter = JokesListAdapter(myActivity, viewModel.jokeListLD.value!!)

        viewModel.jokeListLD.observe(myActivity, object : Observer<List<Joke>> {
            override fun onChanged(jokeList: List<Joke>?) {
                jokeList?.let {
                    val adapter = (jokes_list.adapter as JokesListAdapter)
                    adapter.list = it
                    adapter.notifyDataSetChanged()
                }
            }
        })
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
            holder.setup.text = list[position].setup
            holder.punchline.text = list[position].punchline
        }
    }
}