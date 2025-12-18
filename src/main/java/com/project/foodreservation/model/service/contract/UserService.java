package com.project.foodreservation.model.service.contract;

import com.project.foodreservation.model.da.DAO.DAO;
import com.project.foodreservation.model.entity.User;

public interface UserService extends DAO<User> {

    boolean existsById(Long id);
    boolean existsByUserName(String username);
    User getOneByUserName(String username);

}
