package com.project.foodreservation.model.da.imple;

import com.project.foodreservation.model.da.contract.FoodDA;
import com.project.foodreservation.model.entity.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FoodDAImple implements FoodDA {

    //crud
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Food insert(Food food) {
        entityManager.persist(food);
        return food;
    }

    @Override
    public Food getOneById(Long id) {
        return entityManager.find(Food.class, id);
    }

    @Override
    public List<Food> load() {
        return entityManager.createQuery("SELECT f FROM Food f", Food.class).getResultList();
    }

    @Transactional
    @Override
    public boolean deletedById(Long id) {
        int deletedCount = entityManager.createQuery("DELETE FROM Food f WHERE f.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return deletedCount > 0;
    }

    @Transactional
    @Override
    public Food update(Food tnew) {
        return entityManager.merge(tnew);
    }

    @Override
    public boolean existsByName(String name) {
        Long count = entityManager.createQuery("SELECT COUNT(f) FROM Food f WHERE f.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }


}
