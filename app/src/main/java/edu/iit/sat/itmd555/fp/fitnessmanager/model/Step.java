package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/25/2016.
 */
public class Step {
    private int id;
    private int idUser;
    private String stepsDate;
    private int nbOfSteps;

    public Step() {
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

    public String getStepsDate() {
        return stepsDate;
    }

    public void setStepsDate(String stepsDate) {
        this.stepsDate = stepsDate;
    }

    public int getNbOfSteps() {
        return nbOfSteps;
    }

    public void setNbOfSteps(int nbOfSteps) {
        this.nbOfSteps = nbOfSteps;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", stepsDate='" + stepsDate + '\'' +
                ", nbOfSteps=" + nbOfSteps +
                '}';
    }
}
