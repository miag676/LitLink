package si.fri.prpo.litlink.notifications.services;

import si.fri.prpo.litlink.dtos.AddNotificationDto;
import si.fri.prpo.litlink.notifications.entities.Notification;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NotificationBean {

    private Logger log = Logger.getLogger(NotificationBean.class.getName());

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    private final Client client = ClientBuilder.newClient();
    private final String usersServiceUrl = "http://localhost:8080/v1/users";

    @PostConstruct
    private void init() {
        log.info("Initializing NotificationBean");
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroying NotificationBean");
    }

    @Transactional
    public Notification addNotification(AddNotificationDto dto) {
        log.info("Storing notification for user ID: " + dto.getUserId() + " with message: \"" + dto.getMessage() + "\"");
        Notification notification = new Notification(dto.getUserId(), dto.getMessage());
        em.persist(notification);
        log.info("Notification successfully stored for user ID: " + dto.getUserId());
        return notification;
    }

    @Transactional
    public void addNotificationForAllUsers(String message) {
        log.info("Fetching all user IDs to send notifications with message: \"" + message + "\"");
        List<Integer> userIds = fetchAllUserIds();
        log.info("Total user IDs fetched: " + userIds.size());

        for (Integer userId : userIds) {
            log.info("Processing notification for user ID: " + userId);
            addNotification(new AddNotificationDto(userId, message));
            log.info("Notification added for user ID: " + userId);
        }

        log.info("All notifications successfully processed for message: \"" + message + "\"");
    }

    public List<Notification> getNotificationsByUserId(Integer userId) {
        log.info("Fetching notifications for user ID: " + userId);
        return em.createNamedQuery("notifications.getByUserId", Notification.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional
    public void clearNotificationsByUserId(Integer userId) {
        log.info("Clearing notifications for user ID: " + userId);
        int deletedCount = em.createNamedQuery("notifications.clearByUserId")
                .setParameter("userId", userId)
                .executeUpdate();
        log.info("Cleared " + deletedCount + " notifications for user ID: " + userId);
    }

    private List<Integer> fetchAllUserIds() {
        try {
            log.info("Fetching all user IDs from user service at URL: " + usersServiceUrl + "/ids");
            List<Integer> userIds = client
                    .target(usersServiceUrl + "/ids")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Integer>>() {});
            log.info("Successfully fetched user IDs: " + userIds);
            return userIds;
        } catch (Exception e) {
            log.severe("Failed to fetch user IDs from user service: " + e.getMessage());
            throw new WebApplicationException("Unable to fetch user IDs", 500);
        }
    }
}
