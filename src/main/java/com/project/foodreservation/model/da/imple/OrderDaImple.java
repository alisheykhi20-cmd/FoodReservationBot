package com.project.foodreservation.model.da.imple;

import com.project.foodreservation.model.da.contract.OrderDA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDaImple implements OrderDA {


    @PersistenceContext
    EntityManager entityManager;

    //crud
    @Override
    public Order insert(Order order) {
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order getOneById(Long id) {
        return entityManager.find(Order.class,id);
    }

    @Override
    public List<Order> load() {
        return entityManager.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Transactional
    @Override
    public boolean deletedById(Long id) {
        return entityManager.createQuery("delete from Order o where o.id=:id")
                .setParameter("id",id).executeUpdate()>0;
    }

    @Transactional
    @Override
    public Order update(Order tnew) {
        return entityManager.merge(tnew);
    }
    //operation
    @Override
    public boolean existsById(Long id) {
        Long count=entityManager.createQuery("select count(o) from Order o where o.id=:id", Long.class)
                .setParameter("id",id).getSingleResult();
        return count>0;
    }


}
