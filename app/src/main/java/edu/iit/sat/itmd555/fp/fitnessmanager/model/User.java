package edu.iit.sat.itmd555.fp.fitnessmanager.model;

/**
 * Created by Simon on 4/19/2016.
 */
public class User {
    private int id;
    private String password;
    private String Username;
    private int age;
    private String height;
    private String weight;
    private String email;
    private String gender;
    private int metrics;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMetrics() {
        return metrics;
    }

    public void setMetrics(int metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", Username='" + Username + '\'' +
                ", age=" + age +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", metrics=" + metrics +
                '}';
    }
}
