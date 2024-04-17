package core;

public class DynamicMagnitudeData {
    Double currentMagnitude;
    Double previousMagnitude;
    Double maxMagnitude;
    String maxMagnitudeLocation;

    public Double getCurrentMagnitude() {
        return this.currentMagnitude;
    }

    public void setCurrentMagnitude(Double currentMagnitude) {
        this.currentMagnitude = currentMagnitude;
    }

    public Double getPreviousMagnitude() {
        return this.previousMagnitude;
    }

    public void setPreviousMagnitude(Double previousMagnitude) {
        this.previousMagnitude = previousMagnitude;
    }

    public Double getMaxMagnitude() {
        return this.maxMagnitude;
    }

    public void setMaxMagnitude(Double maxMagnitude) {
        this.maxMagnitude = maxMagnitude;
    }

    public String getMaxMagnitudeLocation() {
        return this.maxMagnitudeLocation;
    }

    public void setMaxMagnitudeLocation(String maxMagnitudeLocation) {
        this.maxMagnitudeLocation = maxMagnitudeLocation;
    }

    public DynamicMagnitudeData clone() {
        DynamicMagnitudeData dynamicMagnitudeData = new DynamicMagnitudeData();
        dynamicMagnitudeData.setCurrentMagnitude(this.currentMagnitude);
        dynamicMagnitudeData.setPreviousMagnitude(this.previousMagnitude);
        dynamicMagnitudeData.setMaxMagnitude(this.maxMagnitude);
        dynamicMagnitudeData.setMaxMagnitudeLocation(this.maxMagnitudeLocation);
        return dynamicMagnitudeData;
    }

}
