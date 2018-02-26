//package com.gmjproductions.dependencyinjectiontest.model2;
//
//import android.arch.lifecycle.ViewModelProviders;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.util.JsonReader;
//
//import com.gmjproductions.dependencyinjectiontest.MainActivity;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.lang.reflect.Type;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//
//import javax.inject.Inject;
//
///**
// * Created by garyjacobs on 1/25/18.
// */
//public class MyAsyncTask extends AsyncTask<String, Integer, ArrayList<Joke>> {
//
//    JokesViewModel jokesViewModel;
//
//    @Inject
//    public MyAsyncTask(MainActivity activity) {
//        jokesViewModel = ViewModelProviders.of(activity).get(JokesViewModel.class);
//    }
//
//    public JokesViewModel getJokesViewModel() {
//        return jokesViewModel;
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
//    }
//
//    @Override
//    protected ArrayList<Joke> doInBackground(String... strings) {
//
//        ArrayList<Joke> jokelist = null;
//        try {
//            URL url = new URL(strings[0]);
//            URLConnection urlc = url.openConnection();
//            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
//            Type token = new TypeToken<ArrayList<Joke>>() {
//            }.getType();
//            jokelist = new Gson().fromJson(br, token);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return jokelist;
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<Joke> jokes) {
//        super.onPostExecute(jokes);
//        jokesViewModel.setJokeList(jokes);
//    }
//}
