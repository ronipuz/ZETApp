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

    @DatabaseField(canBeNull = false)
    private String tramName;

    @DatabaseField(canBeNull = false)
    private Boolean tramBus;

    public Tram() {
        //need empty constructor
    }

    public Tram(int tramNumber, String tramName, boolean tramBus) {
        this.tramNumber = tramNumber;
        this.tramName = tramName;
        this.tramBus = tramBus;
    }

    public Integer getTramNumber() {
        return tramNumber;
    }

    public String getTramName() {
        return tramName;
    }

    public Integer getIdTram() {
        return idTram;
    }

    public boolean getTramBus() {
        return tramBus;
    }

    public void setTramNumber(Integer tramNumber) {
        this.tramNumber = tramNumber;
    }

    public void setTramName(String tramName) {
        this.tramName = tramName;
    }

    public void setTramBus(boolean tramBus) {
        this.tramBus = tramBus;
    }
}
