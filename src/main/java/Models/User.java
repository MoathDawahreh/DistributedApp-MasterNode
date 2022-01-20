package Models;

public class User {
    private String password;
    private String userName;
    private String role;

    public User(String password, String userName, String role) {
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
