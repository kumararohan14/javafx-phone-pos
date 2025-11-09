package service.custom;

import model.Customer;
import model.Item;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ItemService extends SuperService {
    boolean save(Item item) throws SQLException;
    boolean update(Item item) throws SQLException;
    boolean remove(String id) throws SQLException;
    List<Item> getAll() throws SQLException;
    Item searchById(String id) throws SQLException;
    List<String> getItemIds() throws SQLException;

    List<String> getItemCategories() throws SQLException;

    List<String> getSupplierIds() throws SQLException;
    
}
