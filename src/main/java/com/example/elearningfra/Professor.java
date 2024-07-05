package com.example.elearningfra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a professor with personal information and manages local professor data for different modules.
 */
public class Professor {

    private int professorId;
    private String nachname;
    private String vorname;
    private String email;
    private String sprechzimmer;

    private static Map<Integer, List<Professor>> localProfessors = new HashMap<>();

    /**
     * Constructs a new {@code Professor} object.
     *
     * @param professorId The ID of the professor.
     * @param nachname    The last name of the professor.
     * @param vorname     The first name of the professor.
     * @param email       The email address of the professor.
     * @param sprechzimmer The office room of the professor.
     */
    public Professor(int professorId, String nachname, String vorname, String email, String sprechzimmer) {
        this.professorId = professorId;
        this.nachname = nachname;
        this.vorname = vorname;
        this.email = email;
        this.sprechzimmer = sprechzimmer;
    }

    /**
     * Gets the ID of the professor.
     *
     * @return The professor ID.
     */
    public int getProfessorId() {
        return professorId;
    }

    /**
     * Sets the ID of the professor.
     *
     * @param professorId The professor ID.
     */
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    /**
     * Gets the last name of the professor.
     *
     * @return The professor's last name.
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Sets the last name of the professor.
     *
     * @param nachname The professor's last name.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gets the first name of the professor.
     *
     * @return The professor's first name.
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Sets the first name of the professor.
     *
     * @param vorname The professor's first name.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gets the email address of the professor.
     *
     * @return The professor's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the professor.
     *
     * @param email The professor's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the office room of the professor.
     *
     * @return The professor's office room.
     */
    public String getSprechzimmer() {
        return sprechzimmer;
    }

    /**
     * Sets the office room of the professor.
     *
     * @param sprechzimmer The professor's office room.
     */
    public void setSprechzimmer(String sprechzimmer) {
        this.sprechzimmer = sprechzimmer;
    }

    /**
     * Updates the local professor data for a given module ID.
     *
     * @param modulId    The module ID.
     * @param professors The list of professors for the module.
     */
    public static void updateLocalProfessors(int modulId, List<Professor> professors) {
        localProfessors.put(modulId, professors);
    }

    /**
     * Gets the local professor data for a given module ID.
     *
     * @param modulId The module ID.
     * @return A list of professors for the module.
     */
    public static List<Professor> getLocalProfessors(int modulId) {
        return localProfessors.get(modulId);
    }
}
