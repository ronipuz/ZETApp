package hr.fer.tel.ruazosa.model;

/**
 * Created by Jelena on 20.1.2015..
 */

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

@DatabaseTable(tableName = "arrival")
public class Arrival {

    @DatabaseField(generatedId = true)
    private Integer idArrival;

    @DatabaseField(columnName = "DATE", dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @DatabaseField(foreign = true)
    private Ride ride;

    @DatabaseField(foreign = true)
    private Station station;

    public Arrival() {
        //needs empty constructor
    }

    public Arrival(Date time, Ride ride, Station station) {
        this.time = time;
        this.ride = ride;
        this.station = station;
    }

    public Date getTime() {
        return time;
    }

    public Ride getRide() {
        return ride;
    }

    public Station getStation() {
        return station;
    }

    public Integer getIdArrival() {
        return idArrival;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time);
    }
}
