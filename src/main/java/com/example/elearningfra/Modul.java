package com.example.elearningfra;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a module in the e-learning application.
 * This class handles local storage of module names and semester timestamps.
 */
public class Modul {
    private static Map<Integer, Timestamp> semesterTimestamps = new HashMap<>();
    // Map to store local module names with their IDs for each semester
    private static Map<Integer, Map<Integer, String>> localModuleNames = new HashMap<>();

    /**
     * Updates the local cache of module names with their IDs for a specific semester.
     *
     * @param semester       the semester number
     * @param moduleNames    a map of module IDs to module names
     */
    public static void updateLocalModuleNames(int semester, Map<Integer, String> moduleNames) {
        localModuleNames.put(semester, moduleNames);
    }

    /**
     * Retrieves the local cache of module names with their IDs for a specific semester.
     *
     * @param semester the semester number
     * @return a map of module IDs to module names
     */
    public static Map<Integer, String> getLocalModuleNames(int semester) {
        return localModuleNames.get(semester);
    }

    /**
     * Updates the timestamp for a specific semester.
     *
     * @param semester the semester number
     * @param timestamp the timestamp to set for the semester
     */
    public static void updateSemesterTimestamp(int semester, Timestamp timestamp) {
        semesterTimestamps.put(semester, timestamp);
    }

    /**
     * Retrieves the timestamp for a specific semester.
     *
     * @param semester the semester number
     * @return the timestamp associated with the semester
     */
    public static Timestamp getSemesterTimestamp(int semester) {
        return semesterTimestamps.get(semester);
    }
}



