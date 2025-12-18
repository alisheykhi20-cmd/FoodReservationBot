package com.project.foodreservation.model.service.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import com.project.foodreservation.model.entity.Order;

public interface OrderService extends DAO<Order> {

    boolean existsById(Long id);
}
