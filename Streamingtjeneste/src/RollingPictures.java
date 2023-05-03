import java.io.File;
import java.util.ArrayList;

public abstract class RollingPictures implements Media {

    private String title;

    private String year;

    private String category;

    private String rating;
    private String id;

    public RollingPictures(String title, String year,String category, String rating,String id) {
        this.title = title;
        this.year = year;
        this.category = category;
        this.rating = rating;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }
    public String getId() {
        return id;
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