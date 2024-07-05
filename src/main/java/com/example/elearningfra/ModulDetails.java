package com.example.elearningfra;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the details of a module in the e-learning application.
 */
public class ModulDetails {

    private int modulId;
    private String modulName;
    private int cp;
    private int dauer;
    private String pruefung;
    private int semester;
    private String sprache;
    private String koordination;
    private String verfuegbarkeit;
    private String voraussetzung;
    private boolean vorleistung;
    private String inhalt;
    private Timestamp timestampModul;

    private static Map<Integer, ModulDetails> localModulDetails = new HashMap<>();

    /**
     * Constructs a new {@code ModulDetails} object.
     *
     * @param modulId         The ID of the module.
     * @param modulName       The name of the module.
     * @param cp              The credit points for the module.
     * @param dauer           The duration of the module.
     * @param pruefung        The exam details for the module.
     * @param semester        The semester in which the module is taught.
     * @param sprache         The language of instruction for the module.
     * @param koordination   The coordination details for the module.
     * @param verfuegbarkeit  The availability of the module.
     * @param voraussetzung   The prerequisites for the module.
     * @param vorleistung     Whether there is a prerequisite achievement.
     * @param inhalt          The content of the module.
     * @param timestampModul  The timestamp of the module details.
     */
    public ModulDetails(int modulId, String modulName, int cp, int dauer, String pruefung, int semester, String sprache, String koordination, String verfuegbarkeit, String voraussetzung, boolean vorleistung, String inhalt, Timestamp timestampModul) {
        this.modulId = modulId;
        this.modulName = modulName;
        this.cp = cp;
        this.dauer = dauer;
        this.pruefung = pruefung;
        this.semester = semester;
        this.sprache = sprache;
        this.koordination = koordination;
        this.verfuegbarkeit = verfuegbarkeit;
        this.voraussetzung = voraussetzung;
        this.vorleistung = vorleistung;
        this.inhalt = inhalt;
        this.timestampModul = timestampModul;
    }

    // Getters and Setters

    /**
     * Gets the ID of the module.
     *
     * @return The module ID.
     */
    public int getModulId() {
        return modulId;
    }

    /**
     * Sets the ID of the module.
     *
     * @param modulId The module ID.
     */
    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    /**
     * Gets the name of the module.
     *
     * @return The module name.
     */
    public String getModulName() {
        return modulName;
    }

    /**
     * Sets the name of the module.
     *
     * @param modulName The module name.
     */
    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    /**
     * Gets the credit points for the module.
     *
     * @return The credit points.
     */
    public int getCp() {
        return cp;
    }

    /**
     * Sets the credit points for the module.
     *
     * @param cp The credit points.
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * Gets the duration of the module.
     *
     * @return The duration.
     */
    public int getDauer() {
        return dauer;
    }

    /**
     * Sets the duration of the module.
     *
     * @param dauer The duration.
     */
    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    /**
     * Gets the exam details for the module.
     *
     * @return The exam details.
     */
    public String getPruefung() {
        return pruefung;
    }

    /**
     * Sets the exam details for the module.
     *
     * @param pruefung The exam details.
     */
    public void setPruefung(String pruefung) {
        this.pruefung = pruefung;
    }

    /**
     * Gets the semester in which the module is taught.
     *
     * @return The semester.
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets the semester in which the module is taught.
     *
     * @param semester The semester.
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Gets the language of instruction for the module.
     *
     * @return The language of instruction.
     */
    public String getSprache() {
        return sprache;
    }

    /**
     * Sets the language of instruction for the module.
     *
     * @param sprache The language of instruction.
     */
    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    /**
     * Gets the coordination details for the module.
     *
     * @return The coordination details.
     */
    public String getKoordination() {
        return koordination;
    }

    /**
     * Sets the coordination details for the module.
     *
     * @param koordination The coordination details.
     */
    public void setKoordination(String koordination) {
        this.koordination = koordination;
    }

    /**
     * Gets the availability of the module.
     *
     * @return The module availability.
     */
    public String getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    /**
     * Sets the availability of the module.
     *
     * @param verfuegbarkeit The module availability.
     */
    public void setVerfuegbarkeit(String verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    /**
     * Gets the prerequisites for the module.
     *
     * @return The module prerequisites.
     */
    public String getVoraussetzung() {
        return voraussetzung;
    }

    /**
     * Sets the prerequisites for the module.
     *
     * @param voraussetzung The module prerequisites.
     */
    public void setVoraussetzung(String voraussetzung) {
        this.voraussetzung = voraussetzung;
    }

    /**
     * Checks if there is a prerequisite achievement.
     *
     * @return {@code true} if there is a prerequisite achievement; {@code false} otherwise.
     */
    public boolean isVorleistung() {
        return vorleistung;
    }

    /**
     * Sets whether there is a prerequisite achievement.
     *
     * @param vorleistung {@code true} if there is a prerequisite achievement; {@code false} otherwise.
     */
    public void setVorleistung(boolean vorleistung) {
        this.vorleistung = vorleistung;
    }

    /**
     * Gets the content of the module.
     *
     * @return The module content.
     */
    public String getInhalt() {
        return inhalt;
    }

    /**
     * Sets the content of the module.
     *
     * @param inhalt The module content.
     */
    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    /**
     * Gets the timestamp of the module details.
     *
     * @return The timestamp of the module details.
     */
    public Timestamp getTimestampModul() {
        return timestampModul;
    }

    /**
     * Sets the timestamp of the module details.
     *
     * @param timestampModul The timestamp of the module details.
     */
    public void setTimestampModul(Timestamp timestampModul) {
        this.timestampModul = timestampModul;
    }

    /**
     * Updates the local cache of module details.
     *
     * @param modulId        The ID of the module.
     * @param modulDetails  The details of the module.
     */
    public static void updateLocalModulDetails(int modulId, ModulDetails modulDetails) {
        localModulDetails.put(modulId, modulDetails);
    }

    /**
     * Retrieves the local cached module details for a given module ID.
     *
     * @param modulId The ID of the module.
     * @return The module details, or {@code null} if not found.
     */
    public static ModulDetails getLocalModulDetails(int modulId) {
        return localModulDetails.get(modulId);
    }
}
