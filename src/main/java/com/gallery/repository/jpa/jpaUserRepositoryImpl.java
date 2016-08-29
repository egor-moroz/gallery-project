package com.gallery.repository.jpa;

import com.gallery.model.User;
import com.gallery.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class jpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;
    private final Logger logger = LoggerFactory.getLogger(jpaUserRepositoryImpl.class);


    public void saveUser(User user) throws ConstraintViolationException {
        em.persist(user);
    }

    @Override
    public User updateUser(User user) {
        return   em.merge(user);
    }


    public User findByEmail(String email) {
        Query query = this.em.createQuery("select user from User user left join user.pictures where user.email=:email");
        query.setParameter("email",email);
        User user = null;
        try {
            user = (User)query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("No user find");
        }
        return user;
    }

    public List<User> showUsers(){
        return this.em.createQuery("select user from User user").getResultList();
    }
}
