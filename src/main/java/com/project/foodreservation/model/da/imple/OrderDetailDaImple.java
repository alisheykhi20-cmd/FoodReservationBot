package com.project.foodreservation.model.da.imple;
import com.project.foodreservation.model.da.contract.OrderDetailDA;
import com.project.foodreservation.model.entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDetailDaImple implements OrderDetailDA {



        @PersistenceContext
        private EntityManager entityManager;

        //crud
        @Override
        public OrderDetail insert(OrderDetail orderDetail) {
            entityManager.persist(orderDetail);
            return orderDetail;
        }

        @Override
        public OrderDetail getOneById(Long id) {
            return entityManager.find(OrderDetail.class, id);
        }

        @Override
        public List<OrderDetail> load() {
            return entityManager.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class)
                    .getResultList();
        }

        @Transactional
        @Override
        public boolean deletedById(Long id) {
            int deletedCount = entityManager.createQuery("DELETE FROM OrderDetail od WHERE od.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return deletedCount > 0;
        }

        @Transactional
        @Override
        public OrderDetail update(OrderDetail orderDetail) {
            return entityManager.merge(orderDetail);
        }



    //operation



    @Override
    public boolean existsById(Long id) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(od) FROM OrderDetail od WHERE od.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }



    }

