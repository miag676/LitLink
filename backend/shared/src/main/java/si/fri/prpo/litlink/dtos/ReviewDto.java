package si.fri.prpo.litlink.dtos;

public class ReviewDto {
    private Integer userId;
    private Integer bookId;
    private String reviewText;

    public ReviewDto() {}

    public ReviewDto(Integer userId, Integer bookId, String reviewText) {
        this.userId = userId;
        this.bookId = bookId;
        this.reviewText = reviewText;
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

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
