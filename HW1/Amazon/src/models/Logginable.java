package models;

/*
this class is a parent to the classes "Market" and "User"
 */

public abstract class Logginable {
    private String password;
    private String emailAddress;

    public Logginable(String password, String emailAddress) {
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
