package si.fri.prpo.litlink.users.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

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
}
