package com.project.foodreservation.model.service.imple;

import com.project.foodreservation.model.da.contract.UserDA;
import com.project.foodreservation.model.entity.User;
import com.project.foodreservation.model.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserDA userDA;


    //crud
    @Override
    public User insert(User user) {
        if (!userDA.existsByUserName(user.getUsername()))
          return userDA.insert(user);
        return null;
    }

    @Override
    public User getOneById(Long id) {
        return userDA.getOneById(id);
    }

    @Override
    public List<User> load() {
        return userDA.load();
    }

    @Override
    public boolean deletedById(Long id) {
        if (userDA.existsById(id))
          return userDA.deletedById(id);
        return false;
    }

    @Override
    public User update(User tnew) {
        if (userDA.existsById(tnew.getId()))
            return userDA.update(tnew);
        return tnew;
    }


    @Override
    public User getOneByUserName(String username) {
        return userDA.getOneByUserName(username);
    }

    //operation

    @Override
    public boolean existsById(Long id) {
        return userDA.existsById(id);
    }

    @Override
    public boolean existsByUserName(String username) {
        return userDA.existsByUserName(username);
    }




}
