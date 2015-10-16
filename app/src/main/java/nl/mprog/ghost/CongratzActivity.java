// Lirry Pinter ID: 10565051

package nl.mprog.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class CongratzActivity extends ActionBarActivity {

    public String winner_name;
    public String loser_name;
    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratz);

        // Getting data..
        Intent intent = getIntent();
        winner_name = intent.getStringExtra("new highscore");
        loser_name = intent.getStringExtra("no highscore");

        // Setting message
        winner = (TextView) findViewById(R.id.winner_view);
        winner.setText(winner_name);
    }


    // Sending information to highscore activity
    public void on_click_continue(View view) {
        Intent hs = new Intent(getApplicationContext(), HighScoreActivity.class);
        hs.putExtra("new highscore", winner_name);
        hs.putExtra("no highscore", loser_name);
        hs.putExtra("Activity ID", "GameActivity");
        view.getContext().startActivity(hs);
    }
}
