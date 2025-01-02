package si.fri.prpo.litlink.dtos;

import java.util.List;

public class ListDto {
    private Integer userId;
    private String name;
    private List<Integer> bookIds;

    public ListDto() {}

    public ListDto(Integer userId, String name, List<Integer> bookIds) {
        this.userId = userId;
        this.name = name;
        this.bookIds = bookIds;
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

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }
}
