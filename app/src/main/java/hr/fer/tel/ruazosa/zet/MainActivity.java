package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showLinesButton = (Button) this.findViewById(R.id.show_lines_button);
        showLinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                startActivity(intent);
            }
        });

        Button showStopsButton = (Button) this.findViewById(R.id.show_stops_button);
        showStopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StopsActivity.class);
                startActivity(intent);
            }
        });

        Button showNearbyButton = (Button) this.findViewById(R.id.show_nearby_button);
        showNearbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NearbyActivity.class);
                startActivity(intent);
            }
        });
    }
}
