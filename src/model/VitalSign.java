package model;

import jdk.jfr.Name;

public class VitalSign {
    @Name("Systolic Blood Pressure")
    public int bloodPressureSystolic;
    @Name("Diastolic Blood Pressure")
    public int bloodPressureDiastolic;
    @Name("Heart Rate")
    public int heartRate;
    @Name("Body Temperature (C)")
    public double bodyTemperature;

    public VitalSign(int bloodPressureSystolic,
                     int bloodPressureDiastolic,
                     int heartRate,
                     double bodyTemperature) {
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.heartRate = heartRate;
        this.bodyTemperature = bodyTemperature;
    }

    public boolean isNormal() {
        return bloodPressureSystolic >= 90 && bloodPressureSystolic <= 120 &&
               bloodPressureDiastolic >= 60 && bloodPressureDiastolic <= 80 &&
               heartRate >= 40 && heartRate <= 100 &&
               bodyTemperature >= 36.1 && bodyTemperature <= 37.2;
    }

    public VitalSign() {
        this.bloodPressureSystolic = 0;
        this.bloodPressureDiastolic = 0;
        this.heartRate = 0;
        this.bodyTemperature = 0;
    }

    public int getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }

    public void setBloodPressureSystolic(int bloodPressureSystolic) {
        this.bloodPressureSystolic = bloodPressureSystolic;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }
}