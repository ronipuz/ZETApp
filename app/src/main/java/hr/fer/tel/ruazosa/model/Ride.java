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
    private Tram tram;


    @DatabaseField(canBeNull = false)
    private Boolean direction;

    public Integer getIdRide() {
        return idRide;
    }

    public Tram getTram() {
        return tram;
    }


    public Boolean isDirection() {
        return direction;
    }

    public Ride() {
        //needs empty constructor
    }

    public Ride(Tram tram, Boolean direction) {
        this.tram = tram;

        this.direction = direction;
    }

    public void setTram(Tram tram) {
        this.tram = tram;
    }

        public void setDirection(Boolean direction) {
        this.direction = direction;
    }
}
