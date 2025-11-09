package repository.custom;

import model.Item;
import model.Order;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends CrudRepository<Order,String> {

}
