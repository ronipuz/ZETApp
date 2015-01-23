package hr.fer.tel.ruazosa.zet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DepartureView {

    private Date time;
    private String firstName;
    private String lastName;

    public DepartureView (Date time, String firstName, String lastName) {
        this.time = time;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(time) + " " + firstName + " " + lastName;
    }
}
