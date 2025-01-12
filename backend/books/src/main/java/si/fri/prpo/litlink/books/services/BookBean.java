package si.fri.prpo.litlink.books.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

import com.kumuluz.ee.rest.beans.QueryParameters;

import si.fri.prpo.litlink.dtos.AddBookDto;
import si.fri.prpo.litlink.dtos.BookDto;
import si.fri.prpo.litlink.books.entities.Book;

@ApplicationScoped
public class BookBean {

    private Logger log = Logger.getLogger(BookBean.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + BookBean.class.getSimpleName());
        System.out.println("Book bean created");
        // inicializacija virov
    }

    @PreDestroy
    private void destroy() {
        log.info("Deinicializacija zrna " + BookBean.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public List<Book> getAllBooks() {
        List<Book> l = em.createNamedQuery("books.getAll", Book.class).getResultList();
        return l;

    }

    public List<Book> getAllBooks(QueryParameters query) {
        
        // TODO: missing implementation
        
        return null;

    }

    public Long getAllBooksCount(QueryParameters query) {
        
        // TODO: missing implementation

        return null;

    }

    public List<Book> getAllBooksCriteriaAPI() {

        // TODO: missing implementation

        return null;

    }

    public Book getBook(int bookId) {
        return (Book) em.createNamedQuery("books.getById").setParameter("id", bookId).getSingleResult();
    }

    @Transactional
    public Book addBook( AddBookDto book ) {

        Book res = new Book(); 
        res.setTitle(book.getTitle());
        res.setAuthor(book.getAuthor()); 
        res.setReleaseDate(book.getReleaseDate());
        em.persist( res );
        return res;

    }

    @Transactional
    public Book updateBook(int bookId, Book book) {

        // TODO: missing implementation

        return null;

    }

    @Transactional
    public boolean removeBook(int bookId) {

        // TODO: missing implementation

        return false;

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
