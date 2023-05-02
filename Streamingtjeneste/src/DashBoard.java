
public class DashBoard {

    //// Login Part
    UserHandler userHandler = new UserHandler("users.txt");
    TextUI textUI = new TextUI(userHandler);
    MovieHandler movieHandler = new MovieHandler();


    public void setupDashboard() {

        ////Dashboard
        String userChoice = textUI.startMenu();
        while(true) {
            switch (userChoice) {
                case "1":
                    movieHandler.searchmovie();
                    break;
                case "2":
                    movieHandler.searchmoviecategory();
                    //    MediaHandler.movieList();
                    break;
                case "3":
                    MovieHandler.movieList("WatchedMovies.txt");
                    break;
                case "4":
                    MovieHandler.movieList("Favorite.txt");
                    break;
                case "5":
                    movieHandler.showAllMovies();
                    break;
            }
            textUI.backToMenu();
        }
    }

}