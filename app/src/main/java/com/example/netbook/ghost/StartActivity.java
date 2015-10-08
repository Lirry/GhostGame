package com.example.netbook.ghost;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class StartActivity extends ActionBarActivity {

    String player_one_input_string;
    String player_two_input_string;
    CheckBox dutch_checkbox;
    CheckBox english_checkbox;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        dutch_checkbox = (CheckBox) findViewById(R.id.checkbox_dutch);
        english_checkbox = (CheckBox) findViewById(R.id.checkbox_english);
        dutch_checkbox.setChecked(true);
        language = "dutch.txt";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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


    public void on_start_click_go(View view) {
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
            view.getContext().startActivity(i);
        }


    }

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
}
