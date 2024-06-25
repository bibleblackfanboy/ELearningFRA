package com.example.elearningfra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Material {
    private int materialId;
    private int modulId;
    private String typ;
    private String thema;
    private String beschreibung;
    private String url;

    private static Map<Integer, List<Material>> localMaterials = new HashMap<>();

    public Material(int materialId, int modulId, String typ, String thema, String beschreibung, String url) {
        this.materialId = materialId;
        this.modulId = modulId;
        this.typ = typ;
        this.thema = thema;
        this.beschreibung = beschreibung;
        this.url = url;
    }

    // Getters and Setters
    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getModulId() {
        return modulId;
    }

    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static void updateLocalMaterials(int modulId, List<Material> materials) {
        localMaterials.put(modulId, materials);
    }

    public static List<Material> getLocalMaterials(int modulId) {
        return localMaterials.get(modulId);
    }
}