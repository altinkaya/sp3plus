public class Main {

    public static void main(String[] args) {
        UserMenu userMenu = new UserMenu();
        DashBoard dashBoard = new DashBoard();

        userMenu.setupUserMenu();
        dashBoard.setupDashboard();
    }
}