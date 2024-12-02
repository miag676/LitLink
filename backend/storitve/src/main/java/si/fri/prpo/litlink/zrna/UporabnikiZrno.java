package si.fri.prpo.litlink.zrna;

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
import si.fri.prpo.litlink.dtos.UporabnikDto;
import si.fri.prpo.litlink.entitete.Uporabnik;

@ApplicationScoped
public class UporabnikiZrno {

    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UporabnikiZrno.class.getSimpleName());

        // inicializacija virov
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + UporabnikiZrno.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public List<Uporabnik> pridobiUporabnike() {
        List<Uporabnik> l = em.createNamedQuery("users.getAll", Uporabnik.class).getResultList();
        return l;

    }

    public List<Uporabnik> pridobiUporabnike(QueryParameters query) {
        
        // TODO: missing implementation
        
        return null;

    }

    public Long pridobiUporabnikeCount(QueryParameters query) {
        
        // TODO: missing implementation

        return null;

    }

    public List<Uporabnik> pridobiUporabnikeCriteriaAPI() {

        // TODO: missing implementation

        return null;

    }

    public Uporabnik pridobiUporabnika(int uporabnikId) {
        return (Uporabnik) em.createNamedQuery("users.getById").setParameter("id", uporabnikId).getSingleResult();
    }

    @Transactional
    public Uporabnik dodajUporabnika( RegisterDto uporabnik ) {

        Uporabnik upo = new Uporabnik();
        upo.setName(uporabnik.getName()); 
        upo.setUserName(uporabnik.getUserName());
        upo.setLastName(uporabnik.getLastName()); 
        upo.setEmail(uporabnik.getEmail());
        upo.setPassword(uporabnik.getPassword());
        em.persist( upo );
        return upo;

    }

    @Transactional
    public Uporabnik posodobiUporabnika(int uporabnikId, Uporabnik uporabnik) {

        // TODO: missing implementation

        return null;

    }

    @Transactional
    public boolean odstraniUporanbika(int uporabnikId) {

        // TODO: missing implementation

        return false;

    }
}
