// Lirry Pinter ID: 10565051

package nl.mprog.ghost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class RulesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }

    public void on_click_back_rules(View view) {
        Intent back = new Intent(getApplicationContext(), StartActivity.class);
        view.getContext().startActivity(back);
    }
}
