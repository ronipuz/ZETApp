package hr.fer.tel.ruazosa.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import hr.fer.tel.ruazosa.model.Arrival;
import hr.fer.tel.ruazosa.model.Ride;
import hr.fer.tel.ruazosa.model.Station;
import hr.fer.tel.ruazosa.model.Tram;


/**
 * Created by Jelena on 20.1.2015..
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //name of the database
    private static final String DATABASE_NAME = "zet.db";
   //version of database
    private static final int DATABASE_VERSION = 1;

    private Dao<Arrival, Integer> arrivalDao = null;
    private Dao<Ride, Integer> rideDao = null;
    private Dao<Station, Integer> stationDao = null;
    private Dao<Tram, Integer> tramDao = null;

    private RuntimeExceptionDao<Tram, Integer> simpleRuntimeTramDao = null;
    private RuntimeExceptionDao<Arrival, Integer> simpleRuntimeArrivalDao = null;
    private RuntimeExceptionDao<Station, Integer> simpleRuntimeStationDao = null;
    private RuntimeExceptionDao<Ride, Integer> simpleRuntimeRideDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Arrival.class);
            TableUtils.createTable(connectionSource, Ride.class);
            TableUtils.createTable(connectionSource, Station.class);
            TableUtils.createTable(connectionSource, Tram.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Station.class, true);
            TableUtils.dropTable(connectionSource, Arrival.class, true);
            TableUtils.dropTable(connectionSource, Ride.class, true);
            TableUtils.dropTable(connectionSource, Tram.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }

    }


    public Dao<Arrival, Integer> getaArrivalDao() throws SQLException {
        if (arrivalDao == null) {
            arrivalDao = getDao(Arrival.class);
        }
        return arrivalDao;
    }

    public Dao<Ride, Integer> getRideDao() throws SQLException {
        if (rideDao == null) {
            rideDao = getDao(Ride.class);
        }
        return rideDao;
    }

    public Dao<Station, Integer> getStationDao() throws SQLException {
        if (stationDao == null) {
            stationDao = getDao(Station.class);
        }
        return stationDao;
    }

    public Dao<Tram, Integer> getTramDao() throws SQLException {
        if (tramDao == null) {
            tramDao = getDao(Tram.class);
        }
        return tramDao;
    }

    public RuntimeExceptionDao<Tram, Integer> getRuntimeTramDao() {
        if (simpleRuntimeTramDao == null) {
            simpleRuntimeTramDao = getRuntimeExceptionDao(Tram.class);
        }
        return simpleRuntimeTramDao;
    }

    public RuntimeExceptionDao<Arrival, Integer> getRuntimeArrivalDao() {
        if (simpleRuntimeArrivalDao == null) {
            simpleRuntimeArrivalDao = getRuntimeExceptionDao(Arrival.class);
        }
        return simpleRuntimeArrivalDao;
    }

    public RuntimeExceptionDao<Ride, Integer> getRuntimeRideDao() {
        if (simpleRuntimeRideDao == null) {
            simpleRuntimeRideDao = getRuntimeExceptionDao(Ride.class);
        }
        return simpleRuntimeRideDao;
    }

    public RuntimeExceptionDao<Station, Integer> getRuntimeStationDao() {
        if (simpleRuntimeStationDao == null) {
            simpleRuntimeStationDao = getRuntimeExceptionDao(Station.class);
        }
        return simpleRuntimeStationDao;
    }

    @Override
    public void close() {
        super.close();

        arrivalDao = null;
        rideDao = null;
        stationDao = null;
        tramDao = null;
    }
}
