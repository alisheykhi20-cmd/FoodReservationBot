package com.project.foodreservation.model.da.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import com.project.foodreservation.model.entity.Food;

public interface FoodDA extends DAO<Food> {

    boolean existsByName(String name);
}
