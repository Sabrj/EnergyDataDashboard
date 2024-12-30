package com.example.EnergyDataDashboard.classes;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;
import java.time.ZonedDateTime;

public class EnergyData {

    private ZonedDateTime timestamp; // Zeitzonenbewusster Zeitstempel
    private double consumption;      // Energieverbrauch in kWh
    private double cost;             // Kosten in Euro
    private double co2;              // CO2-Emissionen in kg
    private String status;           // Status (Ok, Ersatzwert, etc.)

    public EnergyData(ZonedDateTime timestamp, double consumption, double cost, double co2, String status) {
        this.timestamp = timestamp;
        this.consumption = consumption;
        this.cost = cost;
        this.co2 = co2;
        this.status = status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String toXML(EnergyData energyData) throws Exception {
        JAXBContext context = JAXBContext.newInstance(EnergyData.class);
        Marshaller marshaller = context.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(energyData, writer);
        return writer.toString();
    }
}
