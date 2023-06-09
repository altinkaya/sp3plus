import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class FileIO {

    static final String DB_URL = "jdbc:mysql://localhost/Streaming";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";
    static ArrayList<User> users = new ArrayList<>();
    private static final String FILEPATH = "Favorite.txt";
    private static final String FILEPATH2 = "WatchedMovies.txt";


    public static void saveMovieName(String UserID, String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database loading saveusers");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // the mysql insert statement
            String sql = "INSERT INTO streaming.SavedMovies (UserID,MoviesID) VALUES (?, ?)";

            //INSERT INTO streaming.users (UserName,password) VALUES (?, ?)

            // create the mysql insert preparedstatement
            stmt = conn.prepareStatement(sql);
                stmt.setString ( 1,UserHandler.getId());
                stmt.setString ( 2,id);


            // execute the preparedstatement
            stmt.executeUpdate();


            conn.close();
            DashBoard.setupDashboard();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public static void watchedMovieName(String UserID, String id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database loading saveusers");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // the mysql insert statement
            String sql = "INSERT INTO streaming.watchedmovies (UserID,MoviesID) VALUES (?, ?)";


            // create the mysql insert preparedstatement
            stmt = conn.prepareStatement(sql);
            stmt.setString ( 1,UserHandler.getId());
            stmt.setString ( 2,id);


            // execute the preparedstatement
            stmt.executeUpdate();


            conn.close();
            DashBoard.setupDashboard();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    public static void GetwatchedMovieName() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database loading SavedMovies");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "select * from movies join watchedmovies on movies.id=watchedmovies.moviesid";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                String userID = rs.getString("UserID");
                String movieID = rs.getString("name");

                if (UserHandler.getId().equals(userID)) {
                    System.out.println(movieID);
                }
            }
            //STEP 5: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try

        }
    }


        public static void GetSavedMovieName () {
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                //STEP 1: Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                //STEP 2: Open a connection
                System.out.println("Connecting to database loading SavedMovies");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                //STEP 3: Execute a query
                System.out.println("Creating statement...");
                String sql = "select * from movies join savedmovies on movies.id=savedmovies.moviesid";
                stmt = conn.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery(sql);

                //STEP 4: Extract data from result set
                while (rs.next()) {
                    //Retrieve by column name

                    String userID = rs.getString("UserID");
                    String movieID = rs.getString("name");

                    if (UserHandler.getId().equals(userID)) {
                        System.out.println(movieID);
                    }
                }
                //STEP 5: Clean-up environment
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException se2) {
                }// nothing we can do
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }//end finally try
            }
        }

    }
