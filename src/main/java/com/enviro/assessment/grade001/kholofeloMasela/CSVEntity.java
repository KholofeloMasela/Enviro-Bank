package com.enviro.assessment.grade001.kholofeloMasela;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class CSVEntity {
    private static final String JDBC_URL = "jdbc:h2:mem:AccountProfile";

//    private static final String JDBC_URL = "jdbc:h2:~/test";
    private static final String JDBC_USERNAME = "username";
    private static final String JDBC_PASSWORD = "password";

    public static void main(String[] args) {
        String csvFile = "1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";
        String tableName = "AccountProfile";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            createTableIfNotExists(connection, tableName);
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                insertRecord(connection, tableName, values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTableIfNotExists(Connection connection, String tableName) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT AUTO_INCREMENT," +
                " name VARCHAR(255), surname VARCHAR(255), imageData VARCHAR(255), PRIMARY KEY (id))";
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
        }
    }

    private static void insertRecord(Connection connection, String tableName, String[] values) throws SQLException {
        String insertQuery = "INSERT INTO " + tableName + " (name, age, imageData) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, values[0]);
            statement.setString(2, values[1]);
            statement.setString(2, values[1]);
            statement.executeUpdate();
        }
    }
}