package service.custom;

import model.Item;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface OrderService extends SuperService {
    boolean save(Item item) throws SQLException;
    boolean update(Item item) throws SQLException;
    boolean remove(String id) throws SQLException;
    List<Item> getAll() throws SQLException;
    Item searchById(String id) throws SQLException;
    List<String> getItemIds() throws SQLException;
}
