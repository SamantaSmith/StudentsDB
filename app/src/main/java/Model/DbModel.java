package Model;

public class DbModel {

    private int id;
    private String faculty;
    private String name;
    private String surname;
    private float gpa;

    public DbModel() {
    }

    public DbModel(int id, String faculty, String name, String surname, float gpa) {
        this.id = id;
        this.faculty = faculty;
        this.name = name;
        this.surname = surname;
        this.gpa = gpa;
    }

    public DbModel(String faculty, String name, String surname, float gpa) {
        this.faculty = faculty;
        this.name = name;
        this.surname = surname;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
}
