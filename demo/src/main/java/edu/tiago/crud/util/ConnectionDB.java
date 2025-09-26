package edu.tiago.crud.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static Connection connection = null;
    private Properties properties = loadProperties();
    private String url = properties.getProperty("url");

    public Connection getConnection(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection(url, properties);
            }catch (SQLException e){
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException("Error closing the database connection", e);
            }
        }
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
        return props;
    }
}
