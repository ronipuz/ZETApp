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
    private Float coordinateX ;

    @DatabaseField(canBeNull = false)
    private Float coordinateY ;

    public Station() {
        //need empty constructor
    }

    public Station(String name, Float coordinateX, Float coordinateY) {
        this.name = name;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getName() {
        return name;
    }

    public Integer getIdStation() {
        return idStation;
    }

    public Float getCoordinateX() {
        return coordinateX;
    }

    public Float getCoordinateY() {
        return coordinateY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinateX(Float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(Float coordinateY) {
        this.coordinateY = coordinateY;
    }
}
