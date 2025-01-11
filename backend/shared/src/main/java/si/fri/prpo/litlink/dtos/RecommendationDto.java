package si.fri.prpo.litlink.dtos;

public class RecommendationDto {
    private Integer userId;
    private String bookTitle;
    private String reason;

    public RecommendationDto() {
    }

    public RecommendationDto(Integer userId, String bookTitle, String reason) {
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.reason = reason;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
