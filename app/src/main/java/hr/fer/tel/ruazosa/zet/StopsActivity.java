package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StopsActivity extends ActionBarActivity {

    private static final String LINE_STOP = "line_stop";
    private static final String TRAM_BUS = "tram_bus";

    private boolean tram_bus;
    private boolean dualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        //TODO
        Button mapButton = (Button) findViewById(R.id.stops_map_button);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment stopsFragment = getSupportFragmentManager().findFragmentById(R.id.stops_fragment);
                View fragmentView = stopsFragment.getView();

                int position = 0;

                if (fragmentView != null) {
                    ViewPager stopsPager = (ViewPager)fragmentView.findViewById(R.id.stops_pager);
                    position = stopsPager.getCurrentItem();
                }

                if (position == 0) {
                    tram_bus = true;
                }

                View mapFrame = findViewById(R.id.arrivals_frame);
                dualPane = mapFrame != null && mapFrame.getVisibility() == View.VISIBLE;

                showMap();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_stops, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO
    private void showMap() {
        if (dualPane) {
            MapContainerFragment mapFragment = new MapContainerFragment();
            Bundle args = new Bundle();
            args.putBoolean(LINE_STOP, true);
            args.putBoolean(TRAM_BUS, tram_bus);
            mapFragment.setArguments(args);
            getFragmentManager().beginTransaction().replace(R.id.arrivals_frame, mapFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
        } else {
            Button button = (Button) findViewById(R.id.show_map_button);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(StopsActivity.this, MapActivity.class);
                    intent.putExtra(LINE_STOP, true);
                    intent.putExtra(TRAM_BUS, tram_bus);
                    startActivity(intent);
                }
            });
        }
    }
}
