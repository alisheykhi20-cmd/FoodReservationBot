package com.project.foodreservation.model.da.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import org.springframework.core.annotation.Order;

public interface OrderDA extends DAO<Order> {

    boolean existsById(Long id);
}
