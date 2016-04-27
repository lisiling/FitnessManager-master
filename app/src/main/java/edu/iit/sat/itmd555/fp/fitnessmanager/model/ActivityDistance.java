package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/19/2016.
 */
public class ActivityDistance {
    private int id;
    private int idActivity;
    private String distanceMiles;
    private String distanceKms;
    private String title;

    public ActivityDistance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getDistanceMiles() {
        return distanceMiles;
    }

    public void setDistanceMiles(String distanceMiles) {
        this.distanceMiles = distanceMiles;
    }

    public String getDistanceKms() {
        return distanceKms;
    }

    public void setDistanceKms(String distanceKms) {
        this.distanceKms = distanceKms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ActivityDistance{" +
                "id=" + id +
                ", idActivity=" + idActivity +
                ", distanceMiles='" + distanceMiles + '\'' +
                ", distanceKms='" + distanceKms + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

