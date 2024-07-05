package com.example.elearningfra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a material associated with a specific module in the e-learning application.
 */
public class Material {
    private int materialId;
    private int modulId;
    private String typ;
    private String thema;
    private String beschreibung;
    private String url;

    private static Map<Integer, List<Material>> localMaterials = new HashMap<>();

    /**
     * Constructs a Material object with the specified properties.
     *
     * @param materialId    the ID of the material
     * @param modulId       the ID of the module this material is associated with
     * @param typ           the type of the material
     * @param thema         the theme of the material
     * @param beschreibung  the description of the material
     * @param url           the URL for accessing the material
     */
    public Material(int materialId, int modulId, String typ, String thema, String beschreibung, String url) {
        this.materialId = materialId;
        this.modulId = modulId;
        this.typ = typ;
        this.thema = thema;
        this.beschreibung = beschreibung;
        this.url = url;
    }

    // Getters and Setters

    /**
     * Gets the ID of the material.
     *
     * @return the material ID
     */
    public int getMaterialId() {
        return materialId;
    }

    /**
     * Sets the ID of the material.
     *
     * @param materialId the material ID to set
     */
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    /**
     * Gets the ID of the module this material is associated with.
     *
     * @return the module ID
     */
    public int getModulId() {
        return modulId;
    }

    /**
     * Sets the ID of the module this material is associated with.
     *
     * @param modulId the module ID to set
     */
    public void setModulId(int modulId) {
        this.modulId = modulId;
    }

    /**
     * Gets the type of the material.
     *
     * @return the type of the material
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Sets the type of the material.
     *
     * @param typ the type to set
     */
    public void setTyp(String typ) {
        this.typ = typ;
    }

    /**
     * Gets the theme of the material.
     *
     * @return the theme of the material
     */
    public String getThema() {
        return thema;
    }

    /**
     * Sets the theme of the material.
     *
     * @param thema the theme to set
     */
    public void setThema(String thema) {
        this.thema = thema;
    }

    /**
     * Gets the description of the material.
     *
     * @return the description of the material
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Sets the description of the material.
     *
     * @param beschreibung the description to set
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * Gets the URL for accessing the material.
     *
     * @return the URL of the material
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for accessing the material.
     *
     * @param url the URL to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Updates the local cache of materials for a specific module.
     *
     * @param modulId   the ID of the module
     * @param materials the list of materials to associate with the module
     */
    public static void updateLocalMaterials(int modulId, List<Material> materials) {
        localMaterials.put(modulId, materials);
    }

    /**
     * Retrieves the local cache of materials for a specific module.
     *
     * @param modulId the ID of the module
     * @return the list of materials associated with the module
     */
    public static List<Material> getLocalMaterials(int modulId) {
        return localMaterials.get(modulId);
    }
}
