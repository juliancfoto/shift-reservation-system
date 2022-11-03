package main.dao;

public interface IDao<T> {
   // CRUD: Create | Read | Update | Delete
   T create(T t);
   T read(Long id);
   boolean update(T t);
   boolean delete(Long id);
}