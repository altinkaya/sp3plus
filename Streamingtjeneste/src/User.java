public class User {
    private String name;
    private String password;
    private String id;

    public User(String name, String password, String id) {
        this.id=id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getId(){
        return id;
    }

}