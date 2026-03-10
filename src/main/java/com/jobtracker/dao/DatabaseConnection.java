package com.jobtracker.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    public static void propertiesReader(){
        try {
            Properties props = new Properties();
            InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            props.load(input);
            URL = props.getProperty("db.url");
            USERNAME = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Connection connect(){
        propertiesReader();
        try {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void main(String[] args) {
        Connection connection = connect();
        if(connection != null){
            System.out.println("good");
        }
    }




}