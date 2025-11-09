package service.custom;

import model.Item;
import model.Order;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface OrderService extends SuperService {

    boolean placeOrder(Order order) throws SQLException;
}
