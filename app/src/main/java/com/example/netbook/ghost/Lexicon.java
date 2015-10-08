package com.example.netbook.ghost;


import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Lexicon {

    Set<String> hash_libary;
    ArrayList<String> filter_list;
    String game_word;


    public Lexicon(Context context, String sourcePath) {
        // open file and read into local data structure
        AssetManager assetManager = context.getAssets();
        hash_libary = new HashSet<>();
        BufferedReader b_reader = null;
        try {
            b_reader = new BufferedReader(
                    new InputStreamReader(assetManager.open(sourcePath)));

            // do reading, usually loop until end of file reading
            String add_line = b_reader.readLine();
            while (add_line != null) {
                //process line
                hash_libary.add(add_line);
                add_line = b_reader.readLine();
            }
        } catch (IOException e) {
            //log the exception
            Log.e("IOExeption_TAG", "Catched an error..", e);
        } finally {
            if (b_reader != null) {
                try {
                    b_reader.close();
                } catch (IOException e) {
                    //log the exception
                    Log.e("IOExeption_TAG", "Catched an error..", e);
                }
            }
        }

    }

    public void filter(String game_word_lexicon) {

        // Arraylist is declared to store the elements that contain the game word as prefix
        filter_list = new ArrayList<>();

        // The hashset is iterated to check if it contains the prefix, and stored in the arraylist.
        Iterator<String> iterator = hash_libary.iterator();

        while (iterator.hasNext()) {
            String word = iterator.next();
            if (word.startsWith(game_word_lexicon) && game_word_lexicon != " ") {
                filter_list.add(word);
            }
        }
        if (filter_list != null){
            Log.d("filterlist not empty", "filterlistnotempty");
        }
    }


    public int count() {
        return filter_list.size();
    }


    public String result() {
        return filter_list.iterator().next();
    }

    public void reset(){
        filter_list = new ArrayList<>(hash_libary);
    }



}

