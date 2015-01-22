package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class DetailsActivity extends ActionBarActivity {

    private static final String DEPARTURE_ID = "departureID";

    private boolean dualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.details_fragment, detailsFragment).commit();

        View mapFrame = findViewById(R.id.map_frame);
        dualPane = mapFrame != null && mapFrame.getVisibility() == View.VISIBLE;

        showMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMap() {
        if (dualPane) {
            MapContainerFragment mapFragment = new MapContainerFragment();
            mapFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.map_frame, mapFragment).commit();
        } else {
            Button button = (Button) findViewById(R.id.show_map_button);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(DetailsActivity.this, MapActivity.class);
                    intent.putExtra(DEPARTURE_ID, getIntent().getExtras().getInt(DEPARTURE_ID));
                    startActivity(intent);
                }
            });
        }
    }
}
