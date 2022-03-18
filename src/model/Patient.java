package model;

import jdk.jfr.Name;

import java.util.Date;

public class Patient extends Person {
    @Name("Balance")
    public int balance;
    EncounterHis eh;

    public Patient(int balance) {
        super();
        this.balance = balance;
        this.eh = new EncounterHis();
    }

    public Patient(String name,
                   String gender,
                   Date dob,
                   String address,
                   String community,
                   String city,
                   int balance) {
        super(name, gender, dob, address, community, city);
        this.balance = balance;
        this.eh = new EncounterHis();
    }

    public void add(Encounter e) {
        this.eh.add(e);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public EncounterHis getEh() {
        return eh;
    }

    public void setEh(EncounterHis eh) {
        this.eh = eh;
    }
}
