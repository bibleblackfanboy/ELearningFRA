package com.example.elearningfra;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

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
    public int getModulId() {
        return modulId;
    }

    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String getPruefung() {
        return pruefung;
    }

    public void setPruefung(String pruefung) {
        this.pruefung = pruefung;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public String getKoordination() {
        return koordination;
    }

    public void setKoordination(String koordination) {
        this.koordination = koordination;
    }

    public String getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    public void setVerfuegbarkeit(String verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public String getVoraussetzung() {
        return voraussetzung;
    }

    public void setVoraussetzung(String voraussetzung) {
        this.voraussetzung = voraussetzung;
    }

    public boolean isVorleistung() {
        return vorleistung;
    }

    public void setVorleistung(boolean vorleistung) {
        this.vorleistung = vorleistung;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Timestamp getTimestampModul() {
        return timestampModul;
    }

    public void setTimestampModul(Timestamp timestampModul) {
        this.timestampModul = timestampModul;
    }

    public static void updateLocalModulDetails(int modulId, ModulDetails modulDetails) {
        localModulDetails.put(modulId, modulDetails);
    }

    public static ModulDetails getLocalModulDetails(int modulId) {
        return localModulDetails.get(modulId);
    }
}