package org.example;

import java.sql.*;

public class CallableStatementLab {

    public static String URL = "jdbc:mysql://localhost:3306/SAKILA";
    public static String USER = "root";
    public static String PASSWORD = "mysql";
        public static void main(String[] args) {
            moviesWithCategoriesAndLanguage();
            preparedMoviesWithCategoriesAndLanguage("ENGLISH","ACTION");
            procedureMoviesWithCategoriesAndLanguage("WOMEN","ACTION");
        }

    //4.1 As a user I would like to display the movie title, language and category in the below format.
    private static void moviesWithCategoriesAndLanguage() {
        //        Unhandled exception: java.lang.ClassNotFoundException (checked exception)
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.....");
//            https <protocol> : set of rules governing the transfer of data
//            <protocol><subprotocol><url>
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SAKILA","root","mysql");
            System.out.println("Connected!");
            Statement statement = connection.createStatement();
            System.out.println("Statement created");
            String selectQuery =
                    "SELECT a.TITLE AS TITLE, b.NAME AS LANGUAGE, d.NAME AS CATEGORY FROM FILM a " +
                            "INNER JOIN LANGUAGE b ON a.LANGUAGE_ID  = b.LANGUAGE_ID " +
                            "INNER JOIN FILM_CATEGORY c ON a.FILM_ID  = c.FILM_ID " +
                            "INNER JOIN CATEGORY d ON c.CATEGORY_ID = d.CATEGORY_ID;";

            ResultSet resultSet = statement.executeQuery(selectQuery);
//            Moves the cursor forward one row from its current position.
            while(resultSet.next()){
                String title = resultSet.getString(1);
                String language = resultSet.getString(2);
                String category = resultSet.getString(3);
                System.out.println("Film details : " + title + "\t" + language + "\t" + category + "\t");
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Missing the Driver class!" + classNotFoundException);
        } catch (SQLException e) {
            System.out.println("Error connecting to SQL " + e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Unable to close the connection " + e);
            }
        }
    }

    // 4.2 As a user I would like to display the movie title, language and category in the below format by accepting language and category from the user (hard code values) using PreparedStatement.

    public static void preparedMoviesWithCategoriesAndLanguage(String languageInput, String categoryInput){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.....");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Missing the Driver class!" + classNotFoundException);
            return;
        }

        try(Connection connection =DriverManager.getConnection(URL,USER,PASSWORD)){
            System.out.println("Connected!");

            String preparedSelectQuery =
                    "SELECT a.TITLE AS TITLE, b.NAME AS LANGUAGE, d.NAME AS CATEGORY FROM FILM a " +
                            "INNER JOIN LANGUAGE b ON a.LANGUAGE_ID  = b.LANGUAGE_ID " +
                            "INNER JOIN FILM_CATEGORY c ON a.FILM_ID  = c.FILM_ID " +
                            "INNER JOIN CATEGORY d ON c.CATEGORY_ID = d.CATEGORY_ID WHERE b.NAME = ? AND d.NAME = ?";


            PreparedStatement statement = connection.prepareStatement(preparedSelectQuery);
            statement.setString(1,languageInput);
            statement.setString(2,categoryInput);

            ResultSet resultSet = statement.executeQuery();
            //            Moves the cursor forward one row from its current position.
            while(resultSet.next()){
                String title = resultSet.getString(1);
                String language = resultSet.getString(2);
                String category = resultSet.getString(3);
                System.out.println("Film details : " + title + "\t" + language + "\t" + category + "\t");
            }

        }catch (SQLException e){
            System.out.println("Error connecting to SQL " + e);
        }
    }

    // 4.3 As a user I would like to display the movie title, language and category in a tabular format by accepting both the beginning characters of a movie and category or only beginning characters or category from the user (scanner not necessary) and passing them as parameters to the procedure below.

    /*  Associated procedure

        DROP PROCEDURE IF EXISTS GET_MOVIE_DETAILS;
        DELIMITER //
        CREATE PROCEDURE GET_MOVIE_DETAILS(TITLE_IN CHAR(50), CATEGORY_NAME_IN CHAR(50))
        BEGIN
            SELECT FILM.TITLE, LANGUAGE.NAME AS 'LANGUAGE', CATEGORY.NAME AS 'CATEGORY'
            FROM FILM JOIN LANGUAGE ON FILM.LANGUAGE_ID = LANGUAGE.language_id
            JOIN FILM_CATEGORY ON FILM.FILM_ID = FILM_CATEGORY.FILM_ID
            JOIN CATEGORY ON CATEGORY.category_id = film_category.category_id
                    --  ADD REQUIRED CONDITIONS HERE!
            WHERE (FILM.TITLE LIKE CONCAT(TITLE_IN,'%') AND CATEGORY.NAME LIKE CONCAT(CATEGORY_NAME_IN,'%'))
            OR (FILM.TITLE = '' AND CATEGORY.NAME LIKE CONCAT(CATEGORY_NAME_IN,'%'))
            OR (FILM.TITLE LIKE CONCAT(TITLE_IN,'%') AND CATEGORY.NAME = '');
        END//
        DELIMITER ;
     */

    public static void procedureMoviesWithCategoriesAndLanguage(String titleInput, String categoryInput){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.....");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Missing the Driver class!" + classNotFoundException);
            return;
        }

        try(Connection connection =DriverManager.getConnection(URL,USER,PASSWORD)){
            System.out.println("Connected!");

            CallableStatement statement = connection.prepareCall("{CALL GET_MOVIE_DETAILS(?,?)}");
            statement.setString(1,titleInput);
            statement.setString(2,categoryInput);

            ResultSet resultSet = statement.executeQuery();
            //            Moves the cursor forward one row from its current position.
            while(resultSet.next()){
                String title = resultSet.getString(1);
                String language = resultSet.getString(2);
                String category = resultSet.getString(3);
                System.out.println("Film details : " + title + "\t" + language + "\t" + category + "\t");
            }

        }catch (SQLException e){
            System.out.println("Error connecting to SQL " + e);
        }
    }
}
