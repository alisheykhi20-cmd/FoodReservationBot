package com.project.foodreservation.model.da.imple;

import com.project.foodreservation.model.da.contract.UserDA;
import com.project.foodreservation.model.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

@Repository
@Transactional
public class UserDaImple implements UserDA {

    @PersistenceContext
    EntityManager entityManager;


    /////crud


    @Override
    @Transactional
    public User insert(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getOneById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public User getOneByUserName(String username) {

        try {
            return  (User) entityManager.createQuery("select u from User u where u.username=:username")
                    .setParameter("username",username).getSingleResult();
        }
        catch (Exception e)
        {
            return null;
        }


    }

    @Override
    public List<User> load() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    @Transactional
    public boolean deletedById(Long id) {
        return entityManager.createQuery
                ("delete  from User u where u.id=:id")
                .setParameter("id",id).executeUpdate()>0;
    }

    @Override
    @Transactional
    public User update(User tnew) {
        return entityManager.merge(tnew);
    }


    //operation

    @Override
    public boolean existsById(Long id) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByUserName(String username) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }


}
