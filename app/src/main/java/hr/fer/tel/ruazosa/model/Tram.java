package hr.fer.tel.ruazosa.model;

/**
 * Created by Jelena on 20.1.2015..
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tram")
public class Tram {

    @DatabaseField(generatedId = true)
    private Integer idTram;

    @DatabaseField(canBeNull = false)
    private Integer tramNumber;


    public Tram() {
        //need empty constructor
    }

    public Tram(int tramNumber) {
        this.tramNumber = tramNumber;
    }

    public Integer getTramNumber() {
        return tramNumber;
    }

    public Integer getIdTram() {
        return idTram;
    }

    public void setTramNumber(Integer tramNumber) {
        this.tramNumber = tramNumber;
    }
}
