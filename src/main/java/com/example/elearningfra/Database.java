package com.example.elearningfra;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static final String URL = "jdbc:mariadb://localhost:3306/elearningfh";
    private static final String USER = "root";
    private static final String PASSWORD = "2536";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {

            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;


    }



    public static Map<Integer, String> getModuleNames(int semester) throws SQLException {
        Map<Integer, String> moduleNames = new HashMap<>();
        String query = "SELECT modul_id, modul_name FROM modul WHERE semester = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, semester);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                moduleNames.put(rs.getInt("modul_id"), rs.getString("modul_name"));
            }
        }
        return moduleNames;
    }








    public static ModulDetails getModulDetails(int modulId) throws SQLException {
        String query = "SELECT * FROM modul WHERE modul_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, modulId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ModulDetails(
                        rs.getInt("modul_id"),
                        rs.getString("modul_name"),
                        rs.getInt("cp"),
                        rs.getInt("dauer"),
                        rs.getString("pruefung"),
                        rs.getInt("semester"),
                        rs.getString("sprache"),
                        rs.getString("koordination"),
                        rs.getString("verfuegbarkeit"),
                        rs.getString("voraussetzung"),
                        rs.getBoolean("vorleistung"),
                        rs.getString("inhalt"),
                        rs.getTimestamp("timestamp_modul")
                );
            }
        }
        return null;
    }

    public static List<Professor> getProfessorsForModul(int modulId) throws SQLException {
        List<Professor> professors = new ArrayList<>();
        String query = "SELECT p.* FROM professor p JOIN lehrt l ON p.professor_id = l.professor_id WHERE l.modul_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, modulId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                professors.add(new Professor(
                        rs.getInt("professor_id"),
                        rs.getString("nachname"),
                        rs.getString("vorname"),
                        rs.getString("email"),
                        rs.getString("sprechzimmer")
                ));
            }
        }
        return professors;
    }


    public static List<Material> getMaterialsForModul(int modulId) throws SQLException {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM material WHERE modul_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, modulId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                materials.add(new Material(
                        rs.getInt("material_id"),
                        rs.getInt("modul_id"),
                        rs.getString("typ"),
                        rs.getString("thema"),
                        rs.getString("beschreibung"),
                        rs.getString("url")
                ));
            }
        }
        return materials;
    }






    public static java.sql.Timestamp getTimestamp(int semester) throws SQLException {
        String query = "SELECT timestamp_value FROM timestamp WHERE semester = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, semester);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp("timestamp_value");
            }
        }
        return null;
    }

    public static java.sql.Timestamp getModulTimestamp(int modulId) throws SQLException {
        String query = "SELECT timestamp_modul FROM modul WHERE modul_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, modulId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp("timestamp_modul");
            }
        }
        return null;
    }

    //////Buradan itibaren yeni fonksiyonlar var.


    public static void deleteModule(int modulId) throws SQLException {
        String query = "DELETE FROM modul WHERE modul_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, modulId);
            stmt.executeUpdate();
        }
    }

    public static void addModule(String moduleName, int cp, String pruefung, int semester, String sprache, String koordination, String verfuegbarkeit, String voraussetzung, boolean vorleistung, String inhalt) throws SQLException {
        String query = "INSERT INTO modul (modul_name, cp, pruefung, semester, sprache, koordination, verfuegbarkeit, voraussetzung, vorleistung, inhalt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, moduleName);
            stmt.setInt(2, cp);
            stmt.setString(3, pruefung);
            stmt.setInt(4, semester);
            stmt.setString(5, sprache);
            stmt.setString(6, koordination);
            stmt.setString(7, verfuegbarkeit);
            stmt.setString(8, voraussetzung);
            stmt.setBoolean(9, vorleistung);
            stmt.setString(10, inhalt);
            stmt.executeUpdate();
        }
    }

    public static void updateModule(int modulId, String moduleName, int cp, String pruefung, int semester, String sprache, String koordination, String verfuegbarkeit, String voraussetzung, boolean vorleistung, String inhalt) throws SQLException {
        String query = "UPDATE modul SET modul_name = ?, cp = ?, pruefung = ?, semester = ?, sprache = ?, koordination = ?, verfuegbarkeit = ?, voraussetzung = ?, vorleistung = ?, inhalt = ? WHERE modul_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, moduleName);
            stmt.setInt(2, cp);
            stmt.setString(3, pruefung);
            stmt.setInt(4, semester);
            stmt.setString(5, sprache);
            stmt.setString(6, koordination);
            stmt.setString(7, verfuegbarkeit);
            stmt.setString(8, voraussetzung);
            stmt.setBoolean(9, vorleistung);
            stmt.setString(10, inhalt);
            stmt.setInt(11, modulId);
            stmt.executeUpdate();
        }
    }


    public static Map<Integer, String> getAllModuleNames() throws SQLException {
        Map<Integer, String> moduleNames = new HashMap<>();
        String query = "SELECT modul_id, modul_name FROM modul";
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                moduleNames.put(rs.getInt("modul_id"), rs.getString("modul_name"));
            }
        }
        return moduleNames;
    }


    public static int getLastInsertedModuleId() throws SQLException {
        String query = "SELECT LAST_INSERT_ID() AS last_id";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("last_id");
            }
        }
        return -1;
    }



    public static void addProfessor(Professor professor, int moduleId) throws SQLException {
        String query = "INSERT INTO professor (nachname, vorname, email, sprechzimmer) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, professor.getNachname());
            stmt.setString(2, professor.getVorname());
            stmt.setString(3, professor.getEmail());
            stmt.setString(4, professor.getSprechzimmer());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int professorId = generatedKeys.getInt(1);
                linkProfessorToModule(professorId, moduleId);
            }
        }
    }

    private static void linkProfessorToModule(int professorId, int moduleId) throws SQLException {
        String query = "INSERT INTO lehrt (professor_id, modul_id) VALUES (?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, professorId);
            stmt.setInt(2, moduleId);
            stmt.executeUpdate();
        }
    }



    public static void addMaterial(Material material, int moduleId) throws SQLException {
        String query = "INSERT INTO material (modul_id, typ, thema, beschreibung, url) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, moduleId);
            stmt.setString(2, material.getTyp());
            stmt.setString(3, material.getThema());
            stmt.setString(4, material.getBeschreibung());
            stmt.setString(5, material.getUrl());
            stmt.executeUpdate();
        }
    }



    public static void deleteProfessor(int professorId) throws SQLException {
        String query = "DELETE FROM professor WHERE professor_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, professorId);
            stmt.executeUpdate();
        }
    }

    public static void deleteMaterial(int materialId) throws SQLException {
        String query = "DELETE FROM material WHERE material_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setInt(1, materialId);
            stmt.executeUpdate();
        }
    }



    public static boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM benutzer WHERE benutzername = ? AND passwort = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }



    public static void updateProfessor(Professor professor) throws SQLException {
        String query = "UPDATE professor SET nachname = ?, vorname = ?, email = ?, sprechzimmer = ? WHERE professor_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, professor.getNachname());
            stmt.setString(2, professor.getVorname());
            stmt.setString(3, professor.getEmail());
            stmt.setString(4, professor.getSprechzimmer());
            stmt.setInt(5, professor.getProfessorId());
            stmt.executeUpdate();
        }
    }

    public static void updateMaterial(Material material) throws SQLException {
        String query = "UPDATE material SET typ = ?, thema = ?, beschreibung = ?, url = ? WHERE material_id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setString(1, material.getTyp());
            stmt.setString(2, material.getThema());
            stmt.setString(3, material.getBeschreibung());
            stmt.setString(4, material.getUrl());
            stmt.setInt(5, material.getMaterialId());
            stmt.executeUpdate();
        }
    }






}