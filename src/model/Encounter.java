package model;

import java.util.Date;

public class Encounter {
    private static int sID = 1100;
    private final int id;
    public VitalSign vs;
    public Date time;

    public Encounter(VitalSign vs) {
        this.vs = vs;
        this.id = sID++;
        this.time = new Date();
    }


    public int getId() {
        return id;
    }

    public VitalSign getVs() {
        return vs;
    }

    public void setVs(VitalSign vs) {
        this.vs = vs;
    }

    public String getTime() {
        return time.toString();
    }

    public void setTime(Date time) {
        this.time = time;
    }

}