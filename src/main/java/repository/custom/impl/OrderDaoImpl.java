package repository.custom.impl;


import model.Item;
import model.Order;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.ItemDao;
import repository.custom.OrderDao;
import util.DBConnection;
import util.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class OrderDaoImpl implements OrderDao {

    ItemDao itemDao = DaoFactory.getInstance().getDaoFactoryType(ServiceType.ITEM);

    @Override
    public List<Order> getAll() throws SQLException {
        return List.of();
    }


    @Override
    public boolean save(Order order) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try{



            PreparedStatement pSTM = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
            pSTM.setObject(1, order.getId());
            pSTM.setObject(2, order.getDate());
            pSTM.setObject(3, order.getCustId());
            boolean isOrderSaved = pSTM.executeUpdate() > 0;
            if (isOrderSaved) {
                boolean isOrderDeatilsAdd = OrderDetailsDaoImpl.getInstance().saveOrderDetails(order.getOrderDtails());
                if (isOrderDeatilsAdd) {
                    boolean isUpateStock = itemDao.updateStock(order.getOrderDtails());
                    if (isUpateStock) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;

        }finally {
            connection.setAutoCommit(true);
        }
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
