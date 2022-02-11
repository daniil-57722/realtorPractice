package sample;

public class Realtor {
    private String id;
    private String name;
    private String lastname;
    private String patronymic;
    private String comission;
    private String login;
    private String password;

    public String getId() {
        return id;
    }

    public Realtor(String id, String name, String lastname, String comission, String patronimic, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.comission = comission;
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
