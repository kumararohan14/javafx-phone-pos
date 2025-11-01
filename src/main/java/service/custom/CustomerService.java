package service.custom;

import model.Customer;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SuperService {
    boolean save(Customer customer) throws SQLException;
    boolean update(Customer customer) throws SQLException;
    boolean remove(String id) throws SQLException;
    List<Customer> getAll() throws SQLException;
    Customer searchById(String id) throws SQLException;
    List<String> getIds();
}
