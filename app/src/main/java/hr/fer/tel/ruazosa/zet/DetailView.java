package hr.fer.tel.ruazosa.zet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailView {

    private Date time;
    private String name;

    public DetailView(Date time, String name) {
        this.time = time;
        this.name = name;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time) + " " + name;
    }
}
