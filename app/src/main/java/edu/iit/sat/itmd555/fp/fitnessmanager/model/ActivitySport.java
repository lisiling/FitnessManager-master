package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/18/2016.
 */
public class ActivitySport {
    private int id;
    private int idUser;
    private String type;
    private int durationHours;
    private int durationMinutes;
    private int durationSeconds;
    private String date;
    private String feedback;
    private int justCreated;
    private String title;

    public ActivitySport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getJustCreated() {
        return justCreated;
    }

    public void setJustCreated(int justCreated) {
        this.justCreated = justCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ActivitySport{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", type='" + type + '\'' +
                ", durationHours=" + durationHours +
                ", durationMinutes=" + durationMinutes +
                ", durationSeconds=" + durationSeconds +
                ", date='" + date + '\'' +
                ", feedback='" + feedback + '\'' +
                ", justCreated=" + justCreated +
                ", title='" + title + '\'' +
                '}';
    }
}
