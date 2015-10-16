// Lirry Pinter ID: 10565051

package nl.mprog.ghost;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class GameActivity extends ActionBarActivity {

    private String input;
    private Lexicon lexicon;
    private TextView display_game_word;
    private Game game;
    private String game_word = "";
    public String player_one_string;
    public String player_two_string;
    private TextView player_one_display;
    private TextView player_two_display;
    private TextView player_one_lives;
    private TextView player_two_lives;
    private String language = "dutch.txt";
    private int player_one_lives_count = 0;
    private int player_two_lives_count = 0;
    private final PlayerLives playerLives = new PlayerLives();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        String intent_id = intent.getExtras().getString("Activity ID");


        // Initializing the textviews and class for the lives
        player_one_lives = (TextView) findViewById(R.id.player_one_lives);
        player_two_lives = (TextView) findViewById(R.id.player_two_lives);

        // Getting data from previously entered names, storing them in the name-indicators
        if (intent_id.equals("StartActivity")) {
            player_one_string = intent.getStringExtra("player_one");
            player_two_string = intent.getStringExtra("player_two");
        }
        if (intent_id.equals("HighscoreActivity")) {
            player_one_string = intent.getStringExtra("new player one");
            player_two_string = intent.getStringExtra("new player two");
        }

        player_one_display = (TextView) findViewById(R.id.game_act_player_one);
        player_two_display = (TextView) findViewById(R.id.game_act_player_two);

        player_one_display.setText(player_one_string);
        player_two_display.setText(player_two_string);

        // Getting language preferences
        if (intent_id.equals("StartActivity")) {
            language = intent.getStringExtra("language");
        }

        // Starting a new lexicon with the language preferences
        lexicon = new Lexicon(this, language);
        game = new Game(lexicon);

        // Checking witch language it is
        if (language == "dutch.txt") {
            game.language_switch = true;
        } else
            game.language_switch = false;

        // Setting gameword
        display_game_word = (TextView) findViewById(R.id.display_game_word);

        // Which player's turn it is indicated (player 1 begins)
        player_one_display.setTypeface(null, Typeface.BOLD_ITALIC);


    }


    public void on_click_go(View view) {

        // Store the word in the center of the screen
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
        if (game.ended()) {
            if (game.turn()) {

                // Add penalty-letter to player..
                player_one_lives_count++;
                playerLives.player_one_lives(player_one_lives_count);
                player_one_lives.setText(playerLives.ghost_text_player_one);

                // And reset the game
                lexicon.reset();
                game_word = "";
                display_game_word.setText("");

                if (player_one_lives_count == 5) {
                    Intent end = new Intent(getApplicationContext(), CongratzActivity.class);
                    end.putExtra("new highscore", player_two_string);
                    end.putExtra("no highscore", player_one_string);
                    end.putExtra("Activity ID", "GameActivity");
                    view.getContext().startActivity(end);

                }
            } else {
                // Add penalty-letter to player..
                player_two_lives_count++;
                playerLives.player_two_lives(player_two_lives_count);
                player_two_lives.setText(playerLives.ghost_text_player_two);

                // And reset the game
                lexicon.reset();
                game_word = "";
                display_game_word.setText("");

                // Check if the game is over
                if (player_two_lives_count == 5) {
                    Intent end = new Intent(getApplicationContext(), CongratzActivity.class);
                    end.putExtra("new highscore", player_one_string);
                    end.putExtra("no highscore", player_two_string);
                    end.putExtra("Activity ID", "GameActivity");
                    view.getContext().startActivity(end);
                }
            }
        }

        //Switch the players
        if (game.turn()) {
            player_two_display.setTypeface(null, Typeface.BOLD_ITALIC);
            player_one_display.setTypeface(null, Typeface.NORMAL);
        } else {
            player_one_display.setTypeface(null, Typeface.BOLD_ITALIC);
            player_two_display.setTypeface(null, Typeface.NORMAL);
        }
        game.player_turn = !game.player_turn;
    }

    @Override
    public void onBackPressed() {
    }

    public void on_click_menu_button(View view) {

        // Showing the menu, with restart or change language
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Language button clicked
                        if (game.language_switch()) {
                            language = "english.txt";
                            show_toast("english");
                        } else {
                            language = "dutch.txt";
                            show_toast("dutch");
                        }
                        game_word = "";
                        lexicon.reset();
                        new_lexicon();
                        display_game_word.setText("");
                        game.language_switch = !game.language_switch;
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Restart button clicked
                        lexicon.reset();
                        game_word = "";
                        display_game_word.setText("");
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        // Back button clicked
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Menu").setPositiveButton("Change language", dialogClickListener)
                .setNegativeButton("Restart", dialogClickListener)
                .setNeutralButton("Back", dialogClickListener).show();

    }

    public void show_toast(String lan) {
        Toast.makeText(this, "language is now : " + lan, Toast.LENGTH_LONG).show();
    }

    public void new_lexicon() {
        lexicon = new Lexicon(this, language);
    }

    @Override
    public void onStop() {
        super.onStop();
        save_state(player_one_string,player_two_string,game_word,language);
    }


    // Saves the core variables needed to start a new game
    public void save_state(String player_one_string, String player_two_string, String game_word, String language)
    {
        Save s = new Save();
        s.name_1 = player_one_string;
        s.name_2 = player_two_string;
        s.saved_word = game_word;
        s.saved_language = language;
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream(new File("saved.txt"), false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(s);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }


}


