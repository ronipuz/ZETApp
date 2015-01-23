package hr.fer.tel.ruazosa.zet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hr.fer.tel.ruazosa.model.Arrival;
import hr.fer.tel.ruazosa.model.Ride;
import hr.fer.tel.ruazosa.model.Station;
import hr.fer.tel.ruazosa.model.Tram;

public class MainActivity extends OrmLiteActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO dummy DB
        Tram tram = new Tram(5, "Petica", false);
        Tram tram2 = new Tram(6, "Sestica", false);
        Tram tram3 = new Tram(7, "Sedmica", true);
        Tram tram4 = new Tram(8, "Osmica", true);
        RuntimeExceptionDao<Tram, Integer> tramDao = getHelper().getRuntimeTramDao();
        tramDao.delete(tramDao.queryForAll());
        tramDao.create(tram);
        tramDao.create(tram2);
        tramDao.create(tram3);
        tramDao.create(tram4);

        Station station = new Station("Prva stanica", 0., 0., false);
        Station station2 = new Station("Druga stanica", 0., 0., false);
        Station station3 = new Station("Treca stanica", 0., 0., true);
        Station station4 = new Station("Cetvrat stanica", 0., 0., true);
        RuntimeExceptionDao<Station, Integer> stopDao = getHelper().getRuntimeStationDao();
        stopDao.delete(stopDao.queryForAll());
        stopDao.create(station);
        stopDao.create(station2);
        stopDao.create(station3);
        stopDao.create(station4);

        Ride ride = new Ride(tram, false);
        Ride ride2 = new Ride(tram2,false );
        Ride ride3 = new Ride(tram3, true);
        Ride ride4 = new Ride(tram4,true );
        RuntimeExceptionDao<Ride, Integer> rideDao = getHelper().getRuntimeRideDao();
        rideDao.delete(rideDao.queryForAll());
        /*rideDao.create(ride);
        rideDao.create(ride2);
        rideDao.create(ride3);
        rideDao.create(ride4);*/

        RuntimeExceptionDao<Arrival, Integer> arrivalDao = getHelper().getRuntimeArrivalDao();
        arrivalDao.delete(arrivalDao.queryForAll());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = format.parse("2015-10-13 12:21:22");
            /*arrivalDao.create(new Arrival(format.parse("2015-10-13 12:21:22"),ride,station));
            arrivalDao.create(new Arrival(format.parse("2015-10-13 12:32:22"),ride ,station2));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:57:22"),ride ,station3));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:21:22"),ride2, station));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:32:22"),ride2 ,station2));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:57:22"), ride2, station4));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:21:22"),ride3, station2));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:32:22"),ride3 ,station3));
            arrivalDao.create( new Arrival(format.parse("2015-10-13 12:57:22"), ride3, station4));*/
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
