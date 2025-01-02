package si.fri.prpo.litlink.ratings.services;

import si.fri.prpo.litlink.ratings.entities.Review;
import si.fri.prpo.litlink.dtos.ReviewDto;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReviewsBean {

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public List<ReviewDto> getAllReviews() {
        return em.createQuery("SELECT r FROM Review r", Review.class)
                 .getResultList()
                 .stream()
                 .map(r -> new ReviewDto(r.getUserId(), r.getBookId(), r.getReviewText()))
                 .collect(Collectors.toList());
    }

    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = new Review(reviewDto.getUserId(), reviewDto.getBookId(), reviewDto.getReviewText());
        em.persist(review);
        return reviewDto;
    }

    @Transactional
    public void deleteReview(Integer id) {
        Review review = em.find(Review.class, id);
        if (review != null) {
            em.remove(review);
        }
    }
}
