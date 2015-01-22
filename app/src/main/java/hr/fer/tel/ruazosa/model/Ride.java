package hr.fer.tel.ruazosa.model;

/**
 * Created by Jelena on 20.1.2015..
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ride")
public class Ride {

    @DatabaseField(generatedId = true)
    private Integer idRide;

    @DatabaseField(foreign = true)
    private Tram idTram;

    @DatabaseField(foreign = true)
    private Arrival idArrival;

    @DatabaseField(canBeNull = false)
    private Boolean direction;

    public Integer getIdRide() {
        return idRide;
    }

    public Tram getIdTram() {
        return idTram;
    }

    public Arrival getIdArrival() {
        return idArrival;
    }

    public Boolean isDirection() {
        return direction;
    }

    public Ride() {
        //needs empty constructor
    }

    public Ride(Tram idTram, Arrival idArrival, Boolean direction) {
        this.idTram = idTram;
        this.idArrival = idArrival;
        this.direction = direction;
    }

    public void setIdTram(Tram idTram) {
        this.idTram = idTram;
    }

    public void setIdArrival(Arrival idArrival) {
        this.idArrival = idArrival;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }
}
