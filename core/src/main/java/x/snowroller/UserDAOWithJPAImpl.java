package x.snowroller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAOWithJPAImpl implements UserDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALabb");


    @Override
    public void createUser(User a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        List<User> list;
        EntityManager em = emf.createEntityManager();
        list = em.createQuery("SELECT a FROM User a", User.class).getResultList();
        return list;
    }
}
