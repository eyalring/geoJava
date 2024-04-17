package core;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import EarthQuakesClasses.EarthQuakeOfTheWeek;
import EarthQuakesClasses.Feature;
import utils.Accumulator;
import utils.DateUtils;

public class Processor {
    public static int NON_EXISTING_DAY_OF_MONTH = -1;

    public void handleSameDay(Accumulator accumulator,
            DynamicMagnitudeData dynamicMagnitudeData, double currentIterationMagnitude, String location) {

        accumulator.add(currentIterationMagnitude);

        if (dynamicMagnitudeData.getPreviousMagnitude() == null
                || (dynamicMagnitudeData.getMaxMagnitude() != null
                        && currentIterationMagnitude > dynamicMagnitudeData.getMaxMagnitude())) {
            dynamicMagnitudeData.setMaxMagnitude(new Double(currentIterationMagnitude));
            dynamicMagnitudeData.setMaxMagnitudeLocation(location);
        }
        dynamicMagnitudeData.setPreviousMagnitude(new Double(currentIterationMagnitude));

    }

    public void handleNewDay(Accumulator accumulator,
            DynamicMagnitudeData dynamicMagnitudeData, double currentIterationMagnitude, String location) {

        accumulator.reset();
        accumulator.add(currentIterationMagnitude);
        dynamicMagnitudeData.setMaxMagnitude(new Double(currentIterationMagnitude));
        dynamicMagnitudeData.setMaxMagnitudeLocation(location);
        dynamicMagnitudeData.setPreviousMagnitude(null);

    }

    public void log(Date date, double avgMagnitude, Double maxMagnitude, String maxMagnitudeLocation,
            PrintStream stream) {
        Format f = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = f.format(date);

        String buffer = strDate + " " + String.format("%.2f", avgMagnitude) + ", location: "
                + (maxMagnitude != null ? maxMagnitudeLocation : "NOT FOUND");

        stream.println(buffer);
        System.out.println(buffer);

    }

    public void calculateSummary(EarthQuakeOfTheWeek earthQuakeOfTheWeek) {
        try (PrintStream stream = new PrintStream(
                new FileOutputStream("top_earthquakes_of_the_week_" + new Date().getTime() + ".txt", true))) {

            Date previousIterationDate = null;
            DynamicMagnitudeData dynamicMagnitudeData = new DynamicMagnitudeData();

            Accumulator accumulator = new Accumulator(0, 0);

            if (earthQuakeOfTheWeek.getFeatures() == null) {
                throw new RuntimeException("No earthQuakes data found. Exiting...");
            }

            for (Feature feature : earthQuakeOfTheWeek.getFeatures()) {

                Date currentIterationDate = new Date(feature.getProperties().getTime());
                int currentIterationDayOfMonth = DateUtils.getCurrentDayInMonth(currentIterationDate);
                int previousIterationDayOfMonth = previousIterationDate == null ? NON_EXISTING_DAY_OF_MONTH
                        : DateUtils.getCurrentDayInMonth(previousIterationDate);

                double currentIterationMagnitude = feature.getProperties().getMag();
                String location = feature.getProperties().getPlace();

                if (previousIterationDate == null
                        || currentIterationDayOfMonth == previousIterationDayOfMonth) {
                    this.handleSameDay(accumulator,
                            dynamicMagnitudeData, currentIterationMagnitude, location);

                } else {

                    this.log(previousIterationDate, accumulator.getAverage(),
                            dynamicMagnitudeData.getMaxMagnitude(),
                            dynamicMagnitudeData.getMaxMagnitudeLocation(), stream);

                    this.handleNewDay(accumulator, dynamicMagnitudeData,
                            currentIterationMagnitude, location);
                }

                previousIterationDayOfMonth = currentIterationDayOfMonth;
                previousIterationDate = currentIterationDate;
            }

            this.log(previousIterationDate, accumulator
                    .getAverage(), dynamicMagnitudeData.getMaxMagnitude(),
                    dynamicMagnitudeData.getMaxMagnitudeLocation(), stream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in opening file. Exiting...");
        }
    }
}
