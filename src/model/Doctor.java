package model;

import java.util.Date;

public class Doctor extends Person {
    private static int DoctorId = 10000;
    public final int id;

    public Doctor(String name, String gender, Date dob, String address, String community, String city) {
        super(name, gender, dob, address, community, city);
        this.id = DoctorId++;
    }

    public Doctor() {
        super();
        this.id = DoctorId++;
    }
}
