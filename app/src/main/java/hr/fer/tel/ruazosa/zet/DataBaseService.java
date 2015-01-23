package hr.fer.tel.ruazosa.zet;


import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.io.InterruptedIOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.fer.tel.ruazosa.data.DatabaseHelper;
import hr.fer.tel.ruazosa.model.Arrival;
import hr.fer.tel.ruazosa.model.Ride;
import hr.fer.tel.ruazosa.model.Station;
import hr.fer.tel.ruazosa.model.Tram;
import hr.fer.tel.ruazosa.util.parser.Coordinates;
import hr.fer.tel.ruazosa.util.parser.Detalji;
import hr.fer.tel.ruazosa.util.parser.LinkToDetails;
import hr.fer.tel.ruazosa.util.parser.Raspored;
import hr.fer.tel.ruazosa.util.retrofit.HTMLConverter;
import hr.fer.tel.ruazosa.util.retrofit.ZETService;
import retrofit.RestAdapter;

/**
 * Created by Jelena on 22.1.2015..
 */
public class DataBaseService extends OrmLiteBaseService<DatabaseHelper> {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "pocelo ucitavanje", Toast.LENGTH_LONG).show();

        final String API_URL = "http://zet.hr";
        final RuntimeExceptionDao<Tram, Integer> tramDao = getHelper().getRuntimeTramDao();
        final RuntimeExceptionDao<Ride, Integer> rideDao = getHelper().getRuntimeRideDao();
        final RuntimeExceptionDao<Station, Integer> stationDao = getHelper().getRuntimeStationDao();
        final RuntimeExceptionDao<Arrival, Integer> arrivalDao = getHelper().getRuntimeArrivalDao();

        Thread thread = new Thread() {
            @Override
            public void run() {
                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 String todayDate = "2015-1-23";

                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API_URL)
                        .setConverter(new HTMLConverter())
                        .build();

                ZETService zet = restAdapter.create(ZETService.class);

                List<Tram> trams = zet.trams();

                for (Tram tram : trams) {
                    tramDao.create(tram);
                }

                List<Station> stations = new ArrayList<>();
                List<Ride> rides = new ArrayList<>();
                List<Arrival> arrivals = new ArrayList<>();

                Station nigdje = new Station("Nigdjezemska", 14.5, 56.7, false);
                //stations.add(nigdje);
                Map<String, Station> stationMap = new HashMap<>();
                stationMap.put("nigdjezemska", nigdje);

            //petlja za stanice
            for(Tram tram : trams) {
                Raspored schList = zet.raspored(tram.getTramNumber());

                LinkToDetails link = schList.getRideLinksD0().get(27);
                List<Coordinates> coordinates = schList.getCoordinates();

                Detalji detalji = zet.detalji(link.getRoute_id(), link.getTrip_id(), link.getDirection_id());

                // lista Stations
                for (int i=0; i<detalji.getTimesAndStations().size(); i++) {
                    String stationString = detalji.getTimesAndStations().get(i);
                    String[] stationArr = stationString.split(" - ");
                    Station station;
                    if(coordinates.size()-1 < i) {
                        station = new Station(stationArr[1], coordinates.get(coordinates.size()-1).getX(),
                                coordinates.get(coordinates.size()-1).getY(), false);
                    } else {
                        station = new Station(stationArr[1], coordinates.get(i).getX(),
                                coordinates.get(i).getY(), false);
                    }
                    stationMap.put(stationArr[1], station);
                    stations.add(station);
                }

            }

            for(Tram tram : trams) {
                Raspored schList = zet.raspored(tram.getTramNumber());

                //jedan smjer
                for(LinkToDetails link : schList.getRideLinksD0()) {
                    Detalji detalji = zet.detalji(link.getRoute_id(), link.getTrip_id(), link.getDirection_id());
                    Ride ride = new Ride(tram, true);
                    rides.add(ride);

                    for(String timeStation : detalji.getTimesAndStations()) {

                        String[] timeStationArr = timeStation.split(" - ");
                        String stationTime = timeStationArr[0];
                        String stationName = timeStationArr[1];

                        Arrival arrival = null;

                        try {
                            if(stationMap.containsKey(stationName)) {
                                arrival = new Arrival(format.parse(todayDate + stationTime), ride, stationMap.get(stationName));
                            } else {
                                arrival = new Arrival(format.parse(todayDate + stationTime), ride, stationMap.get("nigdjezemska"));
                            }
                        } catch (ParseException e) {
                        }

                        //System.out.println(arrival.getTime() + arrival.getStation().getName());
                        arrivals.add(arrival);
                    }
                }

                //drugi smjer
                for(LinkToDetails link : schList.getRideLinksD1()) {
                    Detalji detalji = zet.detalji(link.getRoute_id(), link.getTrip_id(), link.getDirection_id());
                    Ride ride = new Ride(tram, false);
                    rides.add(ride);

                    for(String timeStation : detalji.getTimesAndStations()) {

                        String[] timeStationArr = timeStation.split(" - ");
                        String stationTime = timeStationArr[0];
                        String stationName = timeStationArr[1];

                        Arrival arrival = null;

                        try {
                            if(stationMap.containsKey(stationName)) {
                                arrival = new Arrival(format.parse(todayDate + stationTime), ride, stationMap.get(stationName));
                            } else {
                                arrival = new Arrival(format.parse(todayDate + stationTime), ride, stationMap.get("nigdjezemska"));
                            }
                        } catch (ParseException e) {
                        }


                        //System.out.println(arrival.getTime() + arrival.getStation().getName());
                        arrivals.add(arrival);
                    }
                }


            }

        for (Ride ride : rides) {
            rideDao.create(ride);
        }

        for (Station station : stations) {
            stationDao.create(station);
        }

        for (Arrival arrival : arrivals) {
            arrivalDao.create(arrival);
        }

            }
            };

        thread.start();



/*
        Tram tram = new Tram(1, "Ilica-Maksimir", false);
        Tram tram2 = new Tram(2, "Črnomerec-Sava", false);

        Station station = new Station("Mandalićina", 11.11, 12.34 , false);
        Station station1 = new Station("Maksimir", 12.34, 12.53, false);
        Station station2 = new Station("Ilica", 21.53, 45.56, false );

        Ride ride = new Ride(tram, false);
        Ride ride1 = new Ride(tram2,false );
        Date date;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse("2015-10-13 12:21:22");
            Arrival arrival1 = new Arrival(date,ride,station );
            Arrival arrival3 = new Arrival(format.parse("2015-10-13 12:57:22"),ride ,station2);
            Arrival arrival4 = new Arrival(format.parse("2015-10-13 12:21:22"),ride1, station);
            Arrival arrival5 = new Arrival(format.parse("2015-10-13 12:32:22"),ride1 ,station1);
            Arrival arrival6 = new Arrival(format.parse("2015-10-13 12:57:22"), ride1, station2 );
            Arrival arrival2 = new Arrival(format.parse("2015-10-13 12:32:22"),ride ,station1);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        RuntimeExceptionDao<Tram, Integer> tramDao = getHelper().getRuntimeTramDao();
        List<Tram> trams;

        tramDao.create(tram);
        tramDao.create(tram2);
        trams = tramDao.queryForAll();

        Toast.makeText(this, trams.get(0).getTramName(), Toast.LENGTH_LONG).show();

*/

        Toast.makeText(this, "gotovo ucitavanje", Toast.LENGTH_LONG).show();
        stopSelf();

    }


}
