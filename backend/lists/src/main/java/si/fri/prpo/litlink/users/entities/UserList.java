package si.fri.prpo.litlink.lists.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "user_lists") // Updated table name for clarity
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private String name; // List name (e.g., "Favorites", "To Read")

    @ElementCollection
    @CollectionTable(name = "list_books", joinColumns = @JoinColumn(name = "list_id"))
    @Column(name = "book_id")
    private java.util.List<Integer> bookIds = new ArrayList<>();

    public UserList() {}

    public UserList(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(java.util.List<Integer> bookIds) {
        this.bookIds = bookIds;
    }
}
