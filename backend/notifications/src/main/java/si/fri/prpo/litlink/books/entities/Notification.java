package si.fri.prpo.litlink.notifications.entities;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
@NamedQueries({
    @NamedQuery(name = "notifications.getByUserId",
                query = "SELECT n FROM Notification n WHERE n.userId = :userId"),
    @NamedQuery(name = "notifications.clearByUserId",
                query = "DELETE FROM Notification n WHERE n.userId = :userId")
})
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "message")
    private String message;

    public Notification() {
    }

    public Notification(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
