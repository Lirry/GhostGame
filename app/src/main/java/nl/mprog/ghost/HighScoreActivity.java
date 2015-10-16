// Lirry Pinter ID: 10565051

package nl.mprog.ghost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;


public class HighScoreActivity extends ActionBarActivity {

    private ListView listView;
    private String new_highscore_name;
    private String no_highscore_name;
    private ArrayAdapter<HighscoreObject> arrayAdapter;
    private static final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        // Getting the old highscores
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // Getting intents, setting list
        Intent intent = this.getIntent();
        new_highscore_name = intent.getStringExtra("new highscore");
        no_highscore_name = intent.getStringExtra("no highscore");
        listView = (ListView) findViewById(R.id.highscores_list);


        // Checking which activity started this activity
        String intent_id = intent.getExtras().getString("Activity ID");
        if(intent_id.equals("StartActivity")){
            // If the activity is started from the main screen, load and show the highscores
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            String json = sharedpreferences.getString("MyObject", "");
            JsonArray jArray = parser.parse(json).getAsJsonArray();
            List<HighscoreObject> object_list = new ArrayList<>();

            for (JsonElement obj : jArray){
                HighscoreObject new_element = gson.fromJson(obj, HighscoreObject.class);
                object_list.add(new_element);
            }
            // Display the highscore list
            listView = (ListView) findViewById(R.id.highscores_list);
            arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    object_list);
            listView.setAdapter(arrayAdapter);

        }
        if(intent_id.equals("GameActivity")) {
             //If the activity is started from the game, load list and add new highscore
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            String json = sharedpreferences.getString("MyObject", "");
            JsonArray jArray = parser.parse(json).getAsJsonArray();
            List<HighscoreObject> object_list = new ArrayList<>();
            for (JsonElement obj : jArray){
                HighscoreObject new_element = gson.fromJson(obj, HighscoreObject.class);
                object_list.add(new_element);
            }

            // Check if the new highscore name is the same as an old highscore and add a point
            for (HighscoreObject objt : object_list) {
                if (objt.name.equals(new_highscore_name)) {
                    objt.score++;
                    arrayAdapter = new ArrayAdapter<>(
                            this,
                            android.R.layout.simple_list_item_1,
                            object_list);
                    listView.setAdapter(arrayAdapter);

                    // Save the changes
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Gson gson_2 = new Gson();
                    String json_2 = gson_2.toJson(object_list);
                    editor.putString("MyObject", json_2);
                    editor.commit();
                    return;
                }
            }
            // Check if the highscore list is empty
            if (object_list.size() < 1){
                // New highscore
                new_highscore_name = intent.getStringExtra("new highscore");
                listView = (ListView) findViewById(R.id.highscores_list);
                HighscoreObject[] scores = {
                        new HighscoreObject(new_highscore_name, 1)
                };
                arrayAdapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        scores);
                listView.setAdapter(arrayAdapter);

                // Save the new highscore as an object
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson_2 = new Gson();
                String json_2 = gson_2.toJson(scores);
                editor.putString("MyObject", json_2);
                editor.commit();
            }
            else {
                //Put a new highscore in the list
                new_highscore_name = intent.getStringExtra("new highscore");
                listView = (ListView) findViewById(R.id.highscores_list);
                HighscoreObject new_score = new HighscoreObject(new_highscore_name, 1);
                object_list.add(new_score);
                arrayAdapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        object_list);
                listView.setAdapter(arrayAdapter);

                // Save the changes
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Gson gson_2 = new Gson();
                String json_2 = gson_2.toJson(object_list);
                editor.putString("MyObject", json_2);
                editor.commit();
            }
        }

    }

    // Going back to the main screen
    public void back_button_high_score(View view) {
        Intent back = new Intent (getApplicationContext(), StartActivity.class);
        view.getContext().startActivity(back);
    }

    @Override
    public void onBackPressed() {
    }

    // Starting a new game with the same names
    public void on_click_new_game(View view) {
        Intent new_game = new Intent(getApplicationContext(), GameActivity.class);
        new_game.putExtra("Activity ID", "HighscoreActivity");
        new_game.putExtra("new player one", no_highscore_name);
        new_game.putExtra("new player two", new_highscore_name);
        view.getContext().startActivity(new_game);
    }
}
