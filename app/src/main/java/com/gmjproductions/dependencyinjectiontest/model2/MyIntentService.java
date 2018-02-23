package com.gmjproductions.dependencyinjectiontest.model2;

import android.app.IntentService;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


/**
 * Created by garyjacobs on 1/26/18.
 */

public class MyIntentService extends IntentService {
    public static String URLKEY = "URLKEY";
    private AppCompatActivity activity;
    private JokesViewModel jokesViewModel;

    public MyIntentService(String name, AppCompatActivity activity) {
        super(name);
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //jokesViewModel = ViewModelProviders.of().get(JokesViewModel.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String urlS = intent.getExtras().getString(URLKEY);
        try {
            URL url = new URL(urlS);
            URLConnection urlc = url.openConnection();
            Type type = new TypeToken<ArrayList<Joke>>() {}.getType();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            ArrayList<Joke> jokeArrayList = new Gson().fromJson(br,type);
            jokesViewModel.setJokeList(jokeArrayList);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
