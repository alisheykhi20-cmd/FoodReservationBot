package com.project.foodreservation.model.service.imple;

import com.project.foodreservation.model.da.contract.FoodDA;
import com.project.foodreservation.model.entity.Food;
import com.project.foodreservation.model.service.contract.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImple implements FoodService {

    @Autowired
    private FoodDA foodDA;
    //crud

    @Override
    public Food insert(Food food) {
        if (!foodDA.existsByName(food.getName()))
            return foodDA.insert(food);
        return null;
    }

    @Override
    public Food getOneById(Long id) {
        return foodDA.getOneById(id);
    }

    @Override
    public List<Food> load() {
        return foodDA.load();
    }

    @Override
    public boolean deletedById(Long id) {
        if (foodDA.getOneById(id) != null)
            return foodDA.deletedById(id);
        return false;
    }

    @Override
    public Food update(Food food) {
        if (foodDA.getOneById(food.getId()) != null)
            return foodDA.update(food);
        return null;
    }
    //operation


    @Override
    public boolean existsByName(String name) {
        return false;
    }


}
