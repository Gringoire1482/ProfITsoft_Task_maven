package dao;

public interface GenericDAO<T> {
        long create(T entity) throws DatabaseException;
        T read(long id) throws DatabaseException;
        void update(T entity) throws DatabaseException;
        void delete(long id) throws DatabaseException;


}
