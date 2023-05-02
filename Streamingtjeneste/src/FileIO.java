import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class FileIO {
    static ArrayList<User> users = new ArrayList<>();
    private static final String FILEPATH = "Favorite.txt";
    private static final String FILEPATH2 = "WatchedMovies.txt";

    public static void saveMovieName(String name,String movieName) {
        try {
            FileWriter writer = new FileWriter(FILEPATH, true);
            writer.write(name +","+movieName + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving movie name: " + e.getMessage());
        }
    }

    public static void watchedMovieName(String name,String movieName) {
        try {
            FileWriter writer = new FileWriter(FILEPATH2, true);
            writer.write(name +","+ movieName + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving movie name: " + e.getMessage());
        }
    }

    public static void GetwatchedMovieName() {
        try {
            Scanner scanner = new Scanner(new File(FILEPATH2));
            while(scanner.hasNextLine()){
                String input = scanner.nextLine();
                String[] values = input.split(",");
                if(values[0].equalsIgnoreCase(UserHandler.getName())){
                    System.out.println(values[1]);
                }
            }

            scanner.close(); // always remember to close the scanner when you're done with it
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    public static void GetSavedMovieName() {
        try {
            Scanner scanner = new Scanner(new File(FILEPATH));
            while(scanner.hasNextLine()){
                String input = scanner.nextLine();
                String[] values = input.split(",");
                if(values[0].equalsIgnoreCase(UserHandler.getName())){
                    System.out.println(values[1]);
                }
            }

            scanner.close(); // always remember to close the scanner when you're done with it
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }




}