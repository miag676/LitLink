package si.fri.prpo.litlink.lists.services;

import si.fri.prpo.litlink.lists.entities.UserList;
import si.fri.prpo.litlink.dtos.ListDto;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@ApplicationScoped
public class ListsBean {

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public java.util.List<ListDto> getAllLists() {
        return em.createQuery("SELECT l FROM UserList l", UserList.class)
                 .getResultList()
                 .stream()
                 .map(l -> new ListDto(l.getId(), l.getUserId(), l.getName(), l.getBookIds()))
                 .collect(Collectors.toList());
    }

    @Transactional
    public ListDto createList(ListDto listDto) {
        UserList list = new UserList(listDto.getUserId(), listDto.getName());
        list.setBookIds(listDto.getBookIds());
        em.persist(list);
        return listDto;
    }

    @Transactional
    public void addBookToList(Integer listId, Integer bookId) {
        UserList list = em.find(UserList.class, listId);
        if (list != null) {
            list.getBookIds().add(bookId);
            em.merge(list);
        }
    }

    @Transactional
    public void removeBookFromList(Integer listId, Integer bookId) {
        UserList list = em.find(UserList.class, listId);
        if (list != null) {
            list.getBookIds().remove(bookId);
            em.merge(list);
        }
    }

    @Transactional
    public void deleteList(Integer listId) {
        UserList list = em.find(UserList.class, listId);
        if (list != null) {
            em.remove(list);
        }
    }

    public List<ListDto> getUserLists(Integer userId) {
    return em.createQuery("SELECT l FROM UserList l WHERE l.userId = :userId", UserList.class)
             .setParameter("userId", userId)
             .getResultList()
             .stream()
             .map(l -> new ListDto(l.getId(), l.getUserId(), l.getName(), l.getBookIds()))
             .collect(Collectors.toList());
    }

    public List<Integer> getBooksByUser(Integer userId) {
    List<UserList> userLists = em.createQuery("SELECT l FROM UserList l WHERE l.userId = :userId", UserList.class)
                                 .setParameter("userId", userId)
                                 .getResultList();

        return userLists.stream()
                        .flatMap(list -> list.getBookIds().stream()) // Flatten all book IDs across lists
                        .distinct() // Remove duplicate book IDs
                        .collect(Collectors.toList());
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
