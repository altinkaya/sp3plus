

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHandler {
    ArrayList<User> users;
    File file;

    public UserHandler(String filename){
        file = new File(filename);
    }

    public boolean login(String username, String password, String id) {
        for(User user: users){
            if(user.getName().equals(username) && user.getPassword().equals(password) )
                return true;
        }
        return false;
    }

    public boolean createUser(String username, String password, String id) {
        if(!isValid(password)){
            return false;
        } if(!isUserNameValid(username)){
            return false;
        }
        for(User user: users){
            if(user.getName().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password, id));
        return true;
    }



    public void loadUsers(){
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String input = scanner.nextLine();
                String[] values = input.split(",");
                users.add(new User(values[0], values[1], values[2]));
            }
        }
        catch (IOException e){
            System.out.println("The system is not working currently");
        }
    }

    public void saveUsers() {
        try{
            FileWriter writer = new FileWriter(file);
            for(User user:users){
                writer.write(user.getName() + "," + user.getPassword() + "," + user.getId() + "\n");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Sorry, the system is not working currently");
        }
    }

    public boolean isUserNameValid(String username) {
        if(username == null || username.length() > 20 || username.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isValid(String password){
        if(password == null || password.length() < 8 || password.equals("")) {
            return false;
        }
        else {
            return true;
        }
    }


}