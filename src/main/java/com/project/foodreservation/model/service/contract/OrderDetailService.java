package com.project.foodreservation.model.service.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import com.project.foodreservation.model.entity.Order;
import com.project.foodreservation.model.entity.OrderDetail;

public interface OrderDetailService extends DAO<OrderDetail> {

    public boolean existsById(Long id);
}
