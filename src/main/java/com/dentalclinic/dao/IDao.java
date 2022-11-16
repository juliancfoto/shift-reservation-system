package com.dentalclinic.dao;

import java.util.List;

public interface IDao<T> {
   // CRUD: Create | Read | Update | Delete
   T create(T t);
   T read(Long id);
   List<T> readAll();
   boolean update(T t);
   void delete(Long id);
}