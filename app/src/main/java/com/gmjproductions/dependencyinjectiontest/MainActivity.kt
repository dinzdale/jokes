package com.gmjproductions.dependencyinjectiontest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.gmjproductions.dependencyinjectiontest.model2.Joke
import com.gmjproductions.dependencyinjectiontest.model2.JokesViewModel
import com.gmjproductions.dependencyinjectiontest.model2.JokesViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.content_main.joke_list;

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: JokesViewModelFactory
    lateinit var viewModel: JokesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(JokesViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        joke_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel!!.jokeListLD
                .observe(this,
                        object : Observer<List<Joke>> {
                            override fun onChanged(list: List<Joke>?) {
                                list?.let {
                                    (joke_list.adapter as MyAdapter).jokeList = it
                                    joke_list.adapter.notifyDataSetChanged()
                                }
                            }
                        })
        joke_list.adapter = MyAdapter(viewModel.jokeListLD.value!!)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onResume() {
        super.onResume()
        viewModel.fetchJokes()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var joke: TextView = view.findViewById(R.id.joke)
        var punchline: TextView = view.findViewById(R.id.punchline)
    }

    class MyAdapter(list: List<Joke>) : RecyclerView.Adapter<MyViewHolder>() {
        var jokeList: List<Joke>

        init {
            jokeList = list;
        }

        override fun getItemCount(): Int {
            return jokeList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, null));
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            jokeList.let {
                holder.joke.text = jokeList[position].setup
                holder.punchline.text = jokeList[position].punchline
            }
        }
    }
}
