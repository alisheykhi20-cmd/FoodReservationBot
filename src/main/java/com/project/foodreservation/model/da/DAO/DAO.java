package com.project.foodreservation.model.da.DAO;

import java.util.List;

public interface DAO <T>{

    T insert(T t);
    T getOneById(Long id);
    List<T> load();
    boolean deletedById(Long id);
    T update(T tnew);
}
