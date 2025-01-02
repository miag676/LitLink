package si.fri.prpo.litlink.dtos;

public class RatingDto {
    private Integer userId;
    private Integer bookId;
    private Integer rating;

    public RatingDto() {}

    public RatingDto(Integer userId, Integer bookId, Integer rating) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
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
