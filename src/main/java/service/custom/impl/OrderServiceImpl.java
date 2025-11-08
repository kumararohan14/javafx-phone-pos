package service.custom.impl;

import model.Item;
import service.custom.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean save(Item item) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        return false;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Item searchById(String id) throws SQLException {
        return null;
    }

    @Override
    public List<String> getItemIds() throws SQLException {
        return List.of();
    }
}
