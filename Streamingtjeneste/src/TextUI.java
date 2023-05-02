import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class TextUI {
    Scanner scanner;
    UserHandler userHandler;

    public TextUI(UserHandler userHandler){
        this.userHandler = userHandler;
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput(){
        System.out.println("Hello. Would you like to: " + "\n" + "1) Log in or" + "\n" + "2) Create user?" + "\n" + "Please write 1 or 2 and press Enter:");
        return scanner.nextLine();
    }

    public void loginMenu() {
        System.out.println("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        String id = Integer.toString(counter()); ////(UUID.randomUUID().toString();)
        if(userHandler.login(userName, password, id)){
            System.out.println("Welcome " + userName);
        }
        else{
            System.out.println("Sorry, the username or password is incorrect");
            loginMenu();
        }

    }

    public void createUserMenu() {
        System.out.println("Please enter a username: ");
        String userName = scanner.nextLine();
        System.out.println("Please enter a password: ");
        String password = scanner.nextLine();
        String id = Integer.toString(counter());
        if(userHandler.createUser(userName, password, id)){
            System.out.println("Welcome " + userName);
        }
        else{
            System.out.println("Sorry, the username or password can not be used try agin:");
            createUserMenu();
        }
    }

    public int counter(){
        Random rn = new Random();
        int answer = rn.nextInt(100) + 1;
        return answer;
    }

    public String startMenu(){
        System.out.println("Welcome to Mustafa's movies, you can now choose one of the options" + "\n" + "1: Search for a movie" + "\n" + "2: Search a movie in a specific category" + "\n" + "3: The list of the movies you have watched" + "\n" + "4: The list of the movies you have saved" + "\n" + "5: Show all movies");
        return scanner.nextLine();
    }

    public void backToMenu() {
        System.out.println("Press 1 to go back to the start menu, or 2 to close the application.");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                startMenu();
                break;
            case "2":
                System.out.println("Closing the application...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                backToMenu();
                break;
        }
    }

}