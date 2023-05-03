import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieHandler {

    // database URL
    static final String DB_URL = "jdbc:mysql://localhost/streaming";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "123456";
    private List<Movies> movies = readMoviesFromDatabase();


    public List<Movies> searchMovieByName(String movieName) {
        List<Movies> matchingMovies = new ArrayList<>();
        for (Movies movies : this.movies) {
            String name = movies.getTitle();
            if (name.toLowerCase().contains(movieName.toLowerCase())) {
                matchingMovies.add(movies);
            }
        }
        return matchingMovies;
    }
    public List<Movies> searchMovieBycategory(String movieName) {
        List<Movies> matchingMovies = new ArrayList<>();
        for (Movies movies : this.movies) {
            String name = movies.getCategory();
            if (name.toLowerCase().contains(movieName.toLowerCase())) {
                matchingMovies.add(movies);
            }
        }
        return matchingMovies;
    }
    private static List<Movies> readMoviesFromDatabase() {
        List<Movies> movies = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //STEP 1: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM movies";
            stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            //STEP 4: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("Name");
                String year = rs.getString("Year");
                String category = rs.getString("category");
                String rating = rs.getString("Rating");
                String id = rs.getString("id");
                //Create movie object and add to list
                Movies movie = new Movies(name, year, category, rating,id);
                movies.add(movie);
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
        }//end try

        return movies;
    }
/*
   private static List<Movies> readMoviesFromCSV() {
        List<Movies> movies = new ArrayList<>();
        Path pathToFile = Paths.get("Movies.txt");
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                Movies movie = createMovie(parts);
                movies.add(movie);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return movies;
    }

*/

    class MovieMenu {
        public void displayMenu(Movies selectedMovie) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Choose between the following options:");
            System.out.println("1. Save to favorite");
            System.out.println("2. Play " + selectedMovie.getTitle());

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                System.out.println("Movie is saved:" + selectedMovie.getTitle());
                FileIO.saveMovieName(UserHandler.getId(),selectedMovie.getId()); // save movie to favorite movie list.
            } else if (choice.equals("2")) {
                System.out.println("You're now watching " + selectedMovie.getTitle());
                FileIO.watchedMovieName(UserHandler.getName(),selectedMovie.getTitle());
            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
    }
    public void searchmovie() {
        MovieHandler movieHandler = new MovieHandler();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a movie name to search: ");
        String movieName = scanner.nextLine();

        List<Movies> matchingMovies = movieHandler.searchMovieByName(movieName);

        if (matchingMovies.size() == 0) {
            System.out.println("No matching movies found.");
            return;
        }

        System.out.println("Matching movies:");
        for (int i = 0; i < matchingMovies.size(); i++) {
            System.out.println((i + 1) + ". " + matchingMovies.get(i).getTitle());
        }

        System.out.print("Enter the number of the movie to select: ");
        int movieIndex = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (movieIndex < 1 || movieIndex > matchingMovies.size()) {
            System.out.println("Invalid movie number.");
            return;
        }

        Movies selectedMovies = matchingMovies.get(movieIndex - 1);
        System.out.println("Selected movie: " + selectedMovies.getTitle());

        // Call your function on selectedMovie here
        if (selectedMovies.getTitle().contains("")) {
            MovieMenu movieMenu = new MovieMenu();
            movieMenu.displayMenu(selectedMovies);
        }
    }

    public void searchmoviecategory() {
        MovieHandler movieHandler = new MovieHandler();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a category to search: ");
        String movieName = scanner.nextLine();

        List<Movies> matchingMovies = movieHandler.searchMovieBycategory(movieName);

        if (matchingMovies.size() == 0) {
            System.out.println("No matching category found.");
            return;
        }

        System.out.println("Matching movies from category:");
        for (int i = 0; i < matchingMovies.size(); i++) {
            System.out.println((i + 1) + ". " + matchingMovies.get(i).getTitle());
        }

        System.out.print("Enter the number of the movie to select: ");
        int movieIndex = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        if (movieIndex < 1 || movieIndex > matchingMovies.size()) {
            System.out.println("Invalid movie number.");
            return;
        }

        Movies selectedMovies = matchingMovies.get(movieIndex - 1);
        System.out.println("Selected movie: " + selectedMovies.getTitle());

        // Call your function on selectedMovie here
        if (selectedMovies.getTitle().contains("")) {
            MovieMenu movieMenu = new MovieMenu();
            movieMenu.displayMenu(selectedMovies);
        }
    }



    public void showAllMovies() {
        Scanner movieScanner = new Scanner(System.in);
       for (int i = 0; i < movies.size(); i++) {
            Movies movie = movies.get(i);
            System.out.println((i + 1) + ". " + movies.get(i).getTitle());
        }

        System.out.print("Please enter the number of the movie you'd like to select: ");
        int selection = movieScanner.nextInt();
        movieScanner.nextLine();

        if (selection < 1 || selection > movies.size()) {
            System.out.println("Invalid movie number.");
            return;
        }

        Movies selectedMovies = movies.get(selection - 1);
        System.out.println("Selected movie: " + selectedMovies.getTitle());

        if (selectedMovies.getTitle().contains("")) {
            MovieMenu movieMenu = new MovieMenu();
            movieMenu.displayMenu(selectedMovies);
        }
    }
/*

    private static Movies createMovie(String[] metadata) {
        String name = metadata[0];
        String year = metadata[1];
        String category = metadata[2];
        String rating = metadata[3];

        // create and return book of this metadata
        return new Movies(name, year, category, rating);
    }

    public static void movieList(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */





}


