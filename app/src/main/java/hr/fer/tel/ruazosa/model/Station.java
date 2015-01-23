package hr.fer.tel.ruazosa.model;

/**
 * Created by Jelena on 20.1.2015..
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "station")
public class Station {


    @DatabaseField(generatedId = true)
    private Integer idStation;

    @DatabaseField(canBeNull = false)
    private String name ;

    @DatabaseField(canBeNull = false)
    private Double coordinateX ;

    @DatabaseField(canBeNull = false)
    private Double coordinateY ;

    @DatabaseField(canBeNull = false)
    private Boolean tramBus;

    public Station() {
        //need empty constructor
    }

    public Station(String name, Double coordinateX, Double coordinateY, boolean tramBus) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.tramBus = tramBus;
    }

    public String getName() {
        return name;
    }

    public Integer getIdStation() {
        return idStation;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public boolean getTramBus() {
        return tramBus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setTramBus(boolean tramBus) {
        this.tramBus = tramBus;
    }

    @Override
    public String toString() {
        return name;
    }
}
