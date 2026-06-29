package engine;

public class PersonalInfo {
    private final String username;
    private final String password;
    private final String email;
    private final String phone;
    private final String age;
    private final String gender;
    private final String country;

    public PersonalInfo(String username, String password, String email, String phone,
                        String age, String gender, String country) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }
}
