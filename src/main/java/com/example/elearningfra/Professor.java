package com.example.elearningfra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Professor {
    private int professorId;
    private String nachname;
    private String vorname;
    private String email;
    private String sprechzimmer;

    private static Map<Integer, List<Professor>> localProfessors = new HashMap<>();

    public Professor(int professorId, String nachname, String vorname, String email, String sprechzimmer) {
        this.professorId = professorId;
        this.nachname = nachname;
        this.vorname = vorname;
        this.email = email;
        this.sprechzimmer = sprechzimmer;
    }

    // Getters and Setters
    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSprechzimmer() {
        return sprechzimmer;
    }

    public void setSprechzimmer(String sprechzimmer) {
        this.sprechzimmer = sprechzimmer;
    }

    public static void updateLocalProfessors(int modulId, List<Professor> professors) {
        localProfessors.put(modulId, professors);
    }

    public static List<Professor> getLocalProfessors(int modulId) {
        return localProfessors.get(modulId);
    }
}