package si.fri.prpo.litlink.ratings.entities;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer rating; // Rating score (e.g., 1-5)

    public Rating() {}

    public Rating(Integer userId, Integer bookId, Integer rating) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
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

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
