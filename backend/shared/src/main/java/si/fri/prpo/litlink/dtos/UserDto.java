package si.fri.prpo.litlink.dtos;

public class UserDto {
    private Integer id; 
    private String name;

    private String lastName;

    private String userName;

    private String email;

    public UserDto() {
    }

    public UserDto(Integer id, String name, String lastName, String userName, String email) {
        this.id = id; 
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    public Integer getId() { // Add getter for id
        return id;
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
}
