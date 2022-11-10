package com.dentalclinic.dao;

public interface IDao<T> {
   // CRUD: Create | Read | Update | Delete
   T create(T t);
   T read(Long id);
   boolean update(T t);
   void delete(Long id);
}