package com.example.netbook.ghost;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


public class GameActivity extends ActionBarActivity {

    String input;
    Lexicon lexicon;
    TextView display_game_word;
    Game game;
    String game_word = "";
    String player_one_string;
    String player_two_string;
    TextView player_one_display;
    TextView player_two_display;
    String language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();

        // Getting data from previously entered names, storing them in the name-indicators
        player_one_string = intent.getStringExtra("player_one");
        player_two_string = intent.getStringExtra("player_two");

        player_one_display = (TextView) findViewById(R.id.game_act_player_one);
        player_two_display = (TextView) findViewById(R.id.game_act_player_two);

        player_one_display.setText(player_one_string);
        player_two_display.setText(player_two_string);

        // Getting language preferences
        language = intent.getStringExtra("language");
        if (language == null){
            Log.d("language is empty", "empty language");
        }
        else
            Log.d("language is loaded", "loaded language");

        // Starting a new lexicon with the language preferences
        lexicon = new Lexicon(this,language);
        game = new Game(lexicon);

        // Which player's turn it is indicated (player 1 begins)
        player_one_display.setTypeface(null, Typeface.BOLD_ITALIC);








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void on_click_go(View view) {
        EditText input_text = (EditText) findViewById(R.id.text_input);
        input = input_text.getText().toString().toLowerCase();
        display_game_word = (TextView) findViewById(R.id.display_game_word);
        game_word += input;
        display_game_word.setText(game_word);
        game_word = display_game_word.getText().toString().toLowerCase();


        //Remove the keyboard from the view, empty the input line
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        input_text.setText("");

        // Use the filter in lexicon to check if there are words who start with this combination
        game.guess(game_word);

        // Check if somebody lost the game
        if (game.ended()){
            Toast.makeText(this, "Somebody won the game!", Toast.LENGTH_SHORT).show();
        }

        // Switch the players
//        player_switch();

    }

//    public void player_switch() {
//        if  {
//            player_one_display.setTypeface(null, Typeface.BOLD_ITALIC);
//            player_two_display.setTypeface(null, Typeface.NORMAL);
//        } else {
//            player_two_display.setTypeface(null, Typeface.BOLD_ITALIC);
//            player_one_display.setTypeface(null, Typeface.NORMAL);
//        }


}

