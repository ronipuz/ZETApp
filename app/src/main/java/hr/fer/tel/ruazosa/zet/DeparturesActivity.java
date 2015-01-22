package hr.fer.tel.ruazosa.zet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class DeparturesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departures);

        if (getResources().getConfiguration().screenWidthDp >= 720) {
            finish();
            return;
        }

        if (savedInstanceState == null) { //TODO ?

            DeparturesFragment departures = new DeparturesFragment();
            departures.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.departures_fragment, departures).commit();
        }
    }
}
