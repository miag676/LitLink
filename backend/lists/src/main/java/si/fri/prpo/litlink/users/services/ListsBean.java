package si.fri.prpo.litlink.lists.services;

import si.fri.prpo.litlink.lists.entities.UserList;
import si.fri.prpo.litlink.dtos.ListDto;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ListsBean {

    @PersistenceContext(unitName = "litlink-jpa")
    private EntityManager em;

    public java.util.List<ListDto> getAllLists() {
        return em.createQuery("SELECT l FROM UserList l", UserList.class)
                 .getResultList()
                 .stream()
                 .map(l -> new ListDto(l.getUserId(), l.getName(), l.getBookIds()))
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
}
