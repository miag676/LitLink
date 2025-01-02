package si.fri.prpo.litlink.ratings.services;

import si.fri.prpo.litlink.ratings.entities.Rating;
import si.fri.prpo.litlink.dtos.RatingDto;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RatingsBean {

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public List<RatingDto> getAllRatings() {
        return em.createQuery("SELECT r FROM Rating r", Rating.class)
                 .getResultList()
                 .stream()
                 .map(r -> new RatingDto(r.getUserId(), r.getBookId(), r.getRating()))
                 .collect(Collectors.toList());
    }

    @Transactional
    public RatingDto addRating(RatingDto ratingDto) {
        Rating rating = new Rating(ratingDto.getUserId(), ratingDto.getBookId(), ratingDto.getRating());
        em.persist(rating);
        return ratingDto;
    }

    @Transactional
    public void deleteRating(Integer id) {
        Rating rating = em.find(Rating.class, id);
        if (rating != null) {
            em.remove(rating);
        }
    }
}
