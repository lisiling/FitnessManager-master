package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/19/2016.
 */
public class ActivityWorkout {
    private int id;
    private int idActivity;
    private int nbOfRep;
    private String typeOfRep;
    private String title;

    public ActivityWorkout() {
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

    public int getNbOfRep() {
        return nbOfRep;
    }

    public void setNbOfRep(int nbOfRep) {
        this.nbOfRep = nbOfRep;
    }

    public String getTypeOfRep() {
        return typeOfRep;
    }

    public void setTypeOfRep(String typeOfRep) {
        this.typeOfRep = typeOfRep;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ActivityWorkout{" +
                "id=" + id +
                ", idActivity=" + idActivity +
                ", nbOfRep=" + nbOfRep +
                ", typeOfRep='" + typeOfRep + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
