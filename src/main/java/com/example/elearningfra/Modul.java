

package com.example.elearningfra;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modul {
    private static Map<Integer, Timestamp> semesterTimestamps = new HashMap<>();
    // Map to store local module names with their IDs for each semester
    private static Map<Integer, Map<Integer, String>> localModuleNames = new HashMap<>();

    // Method to update local module names with IDs for a specific semester
    public static void updateLocalModuleNames(int semester, Map<Integer, String> moduleNames) {
        localModuleNames.put(semester, moduleNames);
    }

    // Method to get local module names with IDs for a specific semester
    public static Map<Integer, String> getLocalModuleNames(int semester) {
        return localModuleNames.get(semester);
    }

    // Method to update the timestamp for a specific semester
    public static void updateSemesterTimestamp(int semester, Timestamp timestamp) {
        semesterTimestamps.put(semester, timestamp);
    }

    // Method to get the timestamp for a specific semester
    public static Timestamp getSemesterTimestamp(int semester) {
        return semesterTimestamps.get(semester);
    }
}



