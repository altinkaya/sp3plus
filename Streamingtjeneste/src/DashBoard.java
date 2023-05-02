
public class DashBoard {

    //// Login Part
    static UserHandler userHandler = new UserHandler();
    static TextUI textUI = new TextUI(userHandler);
    static MovieHandler movieHandler = new MovieHandler();


    public static void setupDashboard() {

        ////Dashboard
        String userChoice = textUI.startMenu();
        while (true) {
            switch (userChoice) {
                case "1":
                    movieHandler.searchmovie();
                    break;

                case "2":
                    movieHandler.searchmoviecategory();
                    break;

                case "3":
                    FileIO.GetwatchedMovieName();
                    break;
                case "4":
                    FileIO.GetSavedMovieName();
                    break;
                case "5":
                    movieHandler.showAllMovies();
                    break;
            }
            textUI.backToMenu();

        }
    }

}