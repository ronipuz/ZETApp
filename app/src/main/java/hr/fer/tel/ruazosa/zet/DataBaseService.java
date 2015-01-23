package hr.fer.tel.ruazosa.zet;


import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hr.fer.tel.ruazosa.data.DatabaseHelper;
import hr.fer.tel.ruazosa.model.Arrival;
import hr.fer.tel.ruazosa.model.Ride;
import hr.fer.tel.ruazosa.model.Station;
import hr.fer.tel.ruazosa.model.Tram;

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


        stopSelf();

    }


}
