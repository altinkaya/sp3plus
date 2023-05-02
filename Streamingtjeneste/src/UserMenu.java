public class UserMenu {

    UserHandler userHandler = new UserHandler("users.txt");
    TextUI textUI = new TextUI(userHandler);
    public void setupUserMenu() {
            userHandler.loadUsers();

            String userInput = textUI.getUserInput();
            switch (userInput) {
                case "1":
                    textUI.loginMenu();
                    break;
                case "2":
                    textUI.createUserMenu();
                    break;
                default:
                    System.out.println("Goodbye");
            }
            userHandler.saveUsers();
    }
}
