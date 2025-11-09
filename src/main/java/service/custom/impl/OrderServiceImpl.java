package service.custom.impl;

import model.Item;
import model.Order;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.ItemDao;
import repository.custom.OrderDao;
import service.custom.OrderService;
import util.ServiceType;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao daoFactoryType = DaoFactory.getInstance().getDaoFactoryType(ServiceType.ORDER);
    @Override
    public boolean placeOrder(Order order) throws SQLException {
        daoFactoryType.save(order);
        return false;
    }
}
