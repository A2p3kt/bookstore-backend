package dev.toluwalase.bookstore.model;

public class SignupForm {
    private String username = "";
    private String password = "";
    private String passwordCheck = "";
    private String role = "USER";

    public SignupForm() {
    }

    public SignupForm(String username, String password, String passwordCheck, String role) {
        this.username = username;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
