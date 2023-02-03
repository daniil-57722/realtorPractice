package sample.objects;

import org.junit.Ignore;


public class User {
    private String name;
    private String phone;
    private String email;
    private String login;
    private String password;
    private String id;

    public User(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public User(String name, String phone, String email, String login, String password){
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getId() { return id; }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
