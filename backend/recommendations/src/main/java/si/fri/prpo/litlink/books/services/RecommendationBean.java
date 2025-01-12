package si.fri.prpo.litlink.recommendations.services;

import si.fri.prpo.litlink.dtos.RecommendationDto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

@ApplicationScoped
public class RecommendationBean {

    private final String listsServiceUrl = "http://lists-service:8080/v1/lists";
    private final String ratingsServiceUrl = "http://ratings-service:8080/v1/ratings";
    private final String booksServiceUrl = "http://books-service:8080/v1/books";

    private final Client client = ClientBuilder.newClient();

    public List<RecommendationDto> getRecommendationsForUser(Integer userId) {
        List<Integer> interactedBooks = getInteractedBooks(userId);
        List<Integer> ratedBooks = getRatedBooks(userId);
        Map<Integer, Double> globalRatings = getGlobalAverageRatings();

        System.out.println("Interacted Books: " + interactedBooks);
        System.out.println("Rated Books: " + ratedBooks);
        System.out.println("Global Ratings: " + globalRatings);

        if (interactedBooks == null) interactedBooks = new ArrayList<>();
        if (ratedBooks == null) ratedBooks = new ArrayList<>();
        if (globalRatings == null) globalRatings = new HashMap<>();

        List<RecommendationDto> recommendations = new ArrayList<>();
        for (Integer bookId : interactedBooks) {
            // Skip books already rated by the user
            if (!ratedBooks.contains(bookId)) {
                String title = getBookTitle(bookId);
                Double averageRating = globalRatings.getOrDefault(bookId, 0.0); // Default to 0.0 for missing ratings
                recommendations.add(new RecommendationDto(
                    userId,
                    title != null ? title : bookId.toString(), // Use title if available
                    averageRating > 0.0
                        ? "Highly rated book with an average rating of " + averageRating
                        : "Book without sufficient rating data"
                ));
            }
        }

        System.out.println("Recommended: " + recommendations);
        return recommendations;
    }

    private List<Integer> getInteractedBooks(Integer userId) {
        try {
            return client
                    .target(listsServiceUrl + "/user/" + userId + "/books")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Integer>>() {});
        } catch (Exception e) {
            System.err.println("Error fetching interacted books: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Integer> getRatedBooks(Integer userId) {
        try {
            List<Map<String, Object>> ratings = client
                    .target(ratingsServiceUrl + "/user/" + userId)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Map<String, Object>>>() {});

            List<Integer> bookIds = new ArrayList<>();
            for (Map<String, Object> rating : ratings) {
                Object bookId = rating.get("bookId");
                if (bookId instanceof BigDecimal) {
                    bookIds.add(((BigDecimal) bookId).intValue());
                } else if (bookId instanceof Integer) {
                    bookIds.add((Integer) bookId);
                }
            }
            return bookIds;
        } catch (Exception e) {
            System.err.println("Error fetching rated books: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private Map<Integer, Double> getGlobalAverageRatings() {
        try {
            Map<String, Double> stringKeyedMap = client
                    .target(ratingsServiceUrl + "/global")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<Map<String, Double>>() {});

            Map<Integer, Double> integerKeyedMap = new HashMap<>();
            for (Map.Entry<String, Double> entry : stringKeyedMap.entrySet()) {
                integerKeyedMap.put(Integer.parseInt(entry.getKey()), entry.getValue());
            }
            return integerKeyedMap;
        } catch (Exception e) {
            System.err.println("Error fetching global average ratings: " + e.getMessage());
            return new HashMap<>();
        }
    }

    private String getBookTitle(Integer bookId) {
        try {
            Map<String, Object> bookDetails = client
                    .target(booksServiceUrl + "/" + bookId)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<Map<String, Object>>() {});

            return bookDetails != null ? (String) bookDetails.get("title") : null;
        } catch (Exception e) {
            System.err.println("Error fetching book details for bookId " + bookId + ": " + e.getMessage());
            return null;
        }
    }

    public String checkReady(){
        try {
            Response resLists = client.target(listsServiceUrl + "/readiness").request().get();
            if (resLists.getStatus() != 200)
                return "Lists api is not ready";
        } catch (Exception e) {
            return "Lists api is not responding";
        }
        try{
            Response resRatings = client.target(ratingsServiceUrl + "/readiness").request().get();
            if (resRatings.getStatus() != 200)
                return "Ratings api is not ready";
        } catch (Exception e) {
            return "Ratings api is not responding";
        }
        try{
            Response resBooks= client.target(booksServiceUrl + "/readiness").request().get();
            if (resBooks.getStatus() != 200)
                return "Books api is not ready";
        } catch (Exception e) {
            return "Books api is not responding";
        }
        return "ok";
    }
}


