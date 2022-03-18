package model;

import jdk.jfr.Name;

import java.util.Date;

public class Person {
    private static int idCount = 0;
    @Name("ID")
    public final int id;
    @Name("Name")
    public String name;
    @Name("Gender")
    public String gender;
    @Name("Date of Birth")
    public Date dob;
    @Name("Address")
    public String address;
    @Name("Community")
    public String community;
    @Name("City")
    public String city;

    public Person(String name, String gender, Date dob, String address, String community, String city) {
        id = idCount++;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.community = community;
        this.city = city;
    }

    public Person() {
        id = idCount++;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}