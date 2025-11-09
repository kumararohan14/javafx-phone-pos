package repository.custom.impl;


import model.Item;
import model.Order;
import repository.custom.OrderDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {


    @Override
    public List<Order> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Order order) throws SQLException {
        System.out.println("Repository : "+order);
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pSTM = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
        pSTM.setObject(1,order.getId());
        pSTM.setObject(2,order.getDate());
        pSTM.setObject(3,order.getCustId());
        boolean b = pSTM.executeUpdate() > 0;
        if (b){

        }
        return false;
    }

    @Override
    public Order searchId(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean remove(String s) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException {
        return false;
    }
}
