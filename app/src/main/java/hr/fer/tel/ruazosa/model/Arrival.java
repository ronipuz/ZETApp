package hr.fer.tel.ruazosa.model;

/**
 * Created by Jelena on 20.1.2015..
 */

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "arrival")
public class Arrival {

    @DatabaseField(generatedId = true)
    private Integer idArrival;

    @DatabaseField(columnName = "DATE", dataType = DataType.DATE_STRING, format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @DatabaseField(foreign = true)
    private Ride idRide;

    @DatabaseField(foreign = true)
    private Station idStation;

    public Arrival() {
        //needs empty constructor
    }

    public Arrival(Date time, Ride idRide, Station idStation) {
        this.time = time;
        this.idRide = idRide;
        this.idStation = idStation;
    }

    public Date getTime() {
        return time;
    }

    public Ride getIdRide() {
        return idRide;
    }

    public Station getIdStation() {
        return idStation;
    }

    public Integer getIdArrival() {
        return idArrival;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setIdRide(Ride idRide) {
        this.idRide = idRide;
    }

    public void setIdStation(Station idStation) {
        this.idStation = idStation;
    }
}
