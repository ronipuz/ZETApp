package hr.fer.tel.ruazosa.zet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class ArrivalsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        if (getResources().getConfiguration().screenWidthDp >= 720) {
            finish();
            return;
        }

        if (savedInstanceState == null) { //TODO ?

            ArrivalsFragment arrivals = new ArrivalsFragment();
            arrivals.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.arrivals_fragment, arrivals).commit();
        }
    }
}
