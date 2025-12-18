package com.project.foodreservation.model.service.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import com.project.foodreservation.model.entity.Food;

public interface FoodService extends DAO<Food> {
    boolean existsByName(String name);
}
