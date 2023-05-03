import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class FileIO {

    static final String DB_URL = "jdbc:mysql://localhost/world";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Dat-0501.";
    static ArrayList<User> users = new ArrayList<>();
    private static final String FILEPATH = "Favorite.txt";
    private static final String FILEPATH2 = "WatchedMovies.txt";


    public static void saveMovieName(String name, String movieName) {
        try {
            FileWriter writer = new FileWriter(FILEPATH, true);
            writer.write(name + "," + movieName + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving movie name: " + e.getMessage());
        }
    }

    public static void watchedMovieName(String name, String movieName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            //STEP 2: Open a connection
            System.out.println("Connecting to database loading WatchedMovies");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // the mysql insert statement
            String sql = "INSERT INTO streaming.WatchedMovies (userID,movieID) VALUES (?, ?)";

            //INSERT INTO streaming.users (UserName,password) VALUES (?, ?)

            // create the mysql insert preparedstatement
            stmt = conn.prepareStatement(sql);
            for(User user:users){
                stmt.setString ( 1,user.getName());
                stmt.setString ( 2,user.getPassword());
            }

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
            System.out.println("Connecting to database loading users");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM streaming.WatchedMovies";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery(sql);

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name

                String userID = rs.getString("UserID");
                String moviesID = rs.getString("MoviesID");

                    if (UserHandler.getId().equals(userID)) {
                       System.out.println(userID + "," + moviesID);
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
            try {
                Scanner scanner = new Scanner(new File(FILEPATH));
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    String[] values = input.split(",");
                    if (values[0].equalsIgnoreCase(UserHandler.getName())) {
                        System.out.println(values[1]);
                    }
                }

                scanner.close(); // always remember to close the scanner when you're done with it
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        }

    }
