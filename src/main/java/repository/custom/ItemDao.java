package repository.custom;

import model.Item;
import model.OrderDetails;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudRepository<Item,String> {
    boolean updateStock(List<OrderDetails> orderDetails) throws SQLException;
    boolean updateStock(OrderDetails orderDetails) throws SQLException;
}
