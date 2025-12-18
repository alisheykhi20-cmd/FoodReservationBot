package com.project.foodreservation.model.da.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import com.project.foodreservation.model.entity.OrderDetail;

public interface OrderDetailDA extends DAO<OrderDetail> {

    boolean existsById(Long id);


}
