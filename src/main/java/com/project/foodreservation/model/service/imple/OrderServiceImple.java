package com.project.foodreservation.model.service.imple;

import com.project.foodreservation.model.da.contract.OrderDA;
import com.project.foodreservation.model.entity.Order;
import com.project.foodreservation.model.service.contract.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImple implements OrderService {

    @Autowired
    OrderDA orderDA;

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public Order insert(Order order) {
        return null;
    }

    @Override
    public Order getOneById(Long id) {
        return null;
    }

    @Override
    public List<Order> load() {
        return List.of();
    }

    @Override
    public boolean deletedById(Long id) {
        return false;
    }

    @Override
    public Order update(Order tnew) {
        return null;
    }
}
