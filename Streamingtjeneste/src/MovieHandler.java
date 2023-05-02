import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieHandler {

    private List<Movies> movies = readMoviesFromCSV("Movies.txt");


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


    /*public Movies getMovieByNumber(int number) {
        return movies.get(number - 1);
    }
*/

    private static List<Movies> readMoviesFromCSV(String fileName) {
        List<Movies> movies = new ArrayList<>();

        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] parts = line.split(";");

                Movies movie = createMovie(parts);

                // adding book into ArrayList
                movies.add(movie);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return movies;
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
            System.out.println("Choose between the following options:");
            System.out.println("1. Save to favorite");
            System.out.println("2. Play " + selectedMovies.getTitle());

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                FileIO.saveMovieName(selectedMovies.getTitle()); // save movie to favorite movie list.
            } else if (choice.equals("2")) {
                System.out.println("You're now watching " + selectedMovies.getTitle());
                FileIO.watchedMovieName(selectedMovies.getTitle());
            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
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
            System.out.println("Choose between the following options:");
            System.out.println("1. Save to favorite");
            System.out.println("2. Play " + selectedMovies.getTitle());

            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                FileIO.saveMovieName(selectedMovies.getTitle()); // save movie to favorite movie list.
            } else if (choice.equals("2")) {
                System.out.println("You're now watching " + selectedMovies.getTitle());
                FileIO.watchedMovieName(selectedMovies.getTitle());
            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
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

        Movies selectedMovie = movies.get(selection - 1);
        System.out.println("Selected movie: " + selectedMovie.getTitle());

        if (selectedMovie.getTitle().contains("")) {
            System.out.println("Choose between the following options:");
            System.out.println("1. Save to favorite");
            System.out.println("2. Play " + selectedMovie.getTitle());

            String choice2 = movieScanner.nextLine();
            if (choice2.equals("1")) {
                FileIO.saveMovieName(selectedMovie.getTitle());
            } else if (choice2.equals("2")) {
                System.out.println("You're now watching " + selectedMovie.getTitle());
                FileIO.watchedMovieName(selectedMovie.getTitle());
            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
    }


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
}


