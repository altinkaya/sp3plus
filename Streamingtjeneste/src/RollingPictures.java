import java.io.File;
import java.util.ArrayList;

public abstract class RollingPictures implements Media {

    private String title;

    private String year;

    private String category;

    private String rating;

    public RollingPictures(String title, String year,String category, String rating) {
        this.title = title;
        this.year = year;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Title "+ getTitle() +"Year " +getYear()+ "Category" +getCategory()+ "Rating"+getRating();
    }
}