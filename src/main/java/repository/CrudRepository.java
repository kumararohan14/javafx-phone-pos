package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T,ID> extends SuperDao  {
    List<T> getAll() throws SQLException;
    boolean save(T t) throws SQLException;
    T searchId(ID id) throws SQLException;
    boolean remove(ID id) throws SQLException;
    boolean update(T t) throws SQLException;
}
