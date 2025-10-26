package service.custom.impl;

import model.Customer;
import service.custom.ItemService;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean save(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Customer searchById(String id) throws SQLException {
        return null;
    }
}
