package si.fri.prpo.litlink.dtos;

public class RegisterDto {
    private String name;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    public RegisterDto() {
    }

    public RegisterDto(Integer id, String name, String lastName, String userName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String uporabniskoIme) {
        this.userName = uporabniskoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }
}
