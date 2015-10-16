// Lirry Pinter ID: 10565051

package nl.mprog.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class StartActivity extends ActionBarActivity {

    private String player_one_input_string;
    private String player_two_input_string;
    private CheckBox dutch_checkbox;
    private CheckBox english_checkbox;
    private String language;
    private String retrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Setting the layout and default language
        dutch_checkbox = (CheckBox) findViewById(R.id.checkbox_dutch);
        english_checkbox = (CheckBox) findViewById(R.id.checkbox_english);
        dutch_checkbox.setChecked(true);
        language = "dutch.txt";
    }



    public void on_start_click_go(View view) {
            // Putting the entered names in the intent
            EditText player_one_input = (EditText) findViewById(R.id.player_one_input);
            EditText player_two_input = (EditText) findViewById(R.id.player_two_input);

            player_one_input_string = player_one_input.getText().toString();
            player_two_input_string = player_two_input.getText().toString();

            if (player_one_input_string.isEmpty() || player_two_input_string.isEmpty()){
                Toast.makeText(this, "Please fill in your names before starting", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("player_one", player_one_input_string);
                i.putExtra("player_two", player_two_input_string);
                i.putExtra("language", language);
                i.putExtra("Activity ID", "StartActivity");
                view.getContext().startActivity(i);
            }

        }

    // Making sure only one checkbox is checked
    public void english_checkbox_clicked(View view) {
        if (english_checkbox.isChecked()){
            dutch_checkbox.setChecked(false);
            english_checkbox.setClickable(false);
            dutch_checkbox.setClickable(true);
            language = "english.txt";
        }
    }
    public void dutch_checkbox_clicked(View view) {
        if (dutch_checkbox.isChecked()){
            english_checkbox.setChecked(false);
            dutch_checkbox.setClickable(false);
            english_checkbox.setClickable(true);
            language = "dutch.txt";
        }
    }

    // Intent to the highscore activity
    public void on_start_click_high_scores(View view) {
        Intent highscores = new Intent(getApplicationContext(), HighScoreActivity.class);
        highscores.putExtra("Activity ID", "StartActivity");
        view.getContext().startActivity(highscores);
    }

    @Override
    public void onBackPressed() {
    }

    // Intent to the rule activity
    public void on_click_rules(View view) {
        Intent rules = new Intent(getApplicationContext(), RulesActivity.class);
        view.getContext().startActivity(rules);
    }

}
