package si.fri.prpo.litlink.entitete;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries(value =
        {
                @NamedQuery(name = "users.getAll", query = "SELECT u FROM Uporabnik u"),
                @NamedQuery(name = "users.getById",
                        query = "SELECT u FROM Uporabnik u WHERE u.id = :id")
        })
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    public Uporabnik() {

    }

    public Uporabnik(Integer id, String name, String lastName, String userName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name: ");
        sb.append(this.name);
        sb.append("<br/>lastname: ");
        sb.append(this.lastName);
        sb.append("<br/>Uporabnisko name: ");
        sb.append(this.userName);
        sb.append("<br/>Email: ");
        sb.append(this.email);
        return sb.toString();
    }
}
