package com.project.foodreservation.model.service.imple;

import com.project.foodreservation.model.da.contract.OrderDetailDA;
import com.project.foodreservation.model.entity.OrderDetail;
import com.project.foodreservation.model.service.contract.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImple implements OrderDetailService {

    @Autowired
    private OrderDetailDA orderDetailDA;

    //crud

    @Override
    public OrderDetail insert(OrderDetail orderDetail) {
        return orderDetailDA.insert(orderDetail);
    }

    @Override
    public OrderDetail getOneById(Long id) {
        return orderDetailDA.getOneById(id);
    }

    @Override
    public List<OrderDetail> load() {
        return orderDetailDA.load();
    }

    @Override
    public boolean deletedById(Long id) {
        return orderDetailDA.deletedById(id);
    }

    @Override
    public OrderDetail update(OrderDetail tnew) {
        return orderDetailDA.update(tnew);
    }


    //operation


    @Override
    public boolean existsById(Long id) {
        return false;
    }


}
