package si.fri.prpo.litlink.users.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;

import com.kumuluz.ee.rest.beans.QueryParameters;
//import com.kumuluz.ee.rest.utils.JPAUtils;

import si.fri.prpo.litlink.dtos.RegisterDto;
import si.fri.prpo.litlink.dtos.UserDto;
import si.fri.prpo.litlink.users.entities.User;

@ApplicationScoped
public class UserBean {

    private Logger log = Logger.getLogger(UserBean.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UserBean.class.getSimpleName());
        System.out.println("User bean created");
        // inicializacija virov
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UserBean.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public List<User> getAllUsers() {
        List<User> l = em.createNamedQuery("users.getAll", User.class).getResultList();
        return l;

    }

    public List<Integer> getAllUserIds() {
        return em.createQuery("SELECT u.id FROM User u", Integer.class).getResultList();
    }


    public List<User> getAllUsers(QueryParameters query) {
        
        // TODO: missing implementation
        
        return null;

    }

    public Long getAllUsersCount(QueryParameters query) {
        
        // TODO: missing implementation

        return null;

    }

    public List<User> getAllUsersCriteriaAPI() {

        // TODO: missing implementation

        return null;

    }

    public User getUser(int uporabnikId) {
        return (User) em.createNamedQuery("users.getById").setParameter("id", uporabnikId).getSingleResult();
    }

    @Transactional
    public User addUser( RegisterDto user ) {

        User upo = new User();
        upo.setName(user.getName()); 
        upo.setUserName(user.getUserName());
        upo.setLastName(user.getLastName()); 
        upo.setEmail(user.getEmail());
        upo.setPassword(user.getPassword());
        em.persist( upo );
        return upo;

    }

    @Transactional
    public User updateUser(int uporabnikId, User uporabnik) {

        // TODO: missing implementation

        return null;

    }

    @Transactional
    public boolean removeUser(int uporabnikId) {

        // TODO: missing implementation

        return false;

    }

    public User login(String userName, String password) {
        try {
            User user = em.createNamedQuery("users.login", User.class)
                          .setParameter("userName", userName)
                          .setParameter("password", password)
                          .getSingleResult();
            log.info("User logged in successfully: " + userName);
            return user;
        } catch (NoResultException e) {
            log.warning("Login failed for user: " + userName);
            return null;
        }
    }

    public List<UserDto> fetchAllUsers() {
        List<User> users = getAllUsers(); // Existing method to get all users
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getName(), user.getLastName(), user.getUserName(), user.getEmail()));
        }
        return userDtos;
    }

    public boolean checkDBConn(){
        try {
            // Attempt a simple query to check the DB connection
            em.createNativeQuery("SELECT 1").getSingleResult();
            return true; // Return 200 if the database is reachable
        } catch (Exception e) {
            // If the query fails, return 500
            return false;
        }
    }
}
