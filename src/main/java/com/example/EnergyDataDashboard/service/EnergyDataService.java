package com.example.EnergyDataDashboard.service;

import com.example.EnergyDataDashboard.classes.EnergyData;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;

@Service
public class EnergyDataService {

    // Diese Methode gibt eine Liste von Listen von Strings zurück
    public List<EnergyData> getEnergyData() {
        List<EnergyData> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/energy_data.csv")))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values[4].equals("Ersatzwert")) {
                    values[4] = "Ok";
                }
                String timestamp = values[0];
                String startTimeStamp = timestamp.split(" - ")[0];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(startTimeStamp, formatter);

                ZoneId zoneId = ZoneId.of("Europe/Vienna");

                ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId.getRules().getOffset(localDateTime));
                
                double consumption = parseDoubleWithComma(values[1]);
                double cost = parseDoubleWithComma(values[2]);
                double co2 = parseDoubleWithComma(values[3]);

                EnergyData energyData = new EnergyData(zonedDateTime, consumption, cost, co2, values[4]);

                data.add(energyData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private double parseDoubleWithComma(String value) {
        return Double.parseDouble(value.replace(",", "."));
    }
}
