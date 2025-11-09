package repository.custom.impl;

import model.OrderDetails;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl {
    private static OrderDetailsDaoImpl instance;

    private OrderDetailsDaoImpl(){

    }
    public static OrderDetailsDaoImpl getInstance(){
        return instance==null?instance=new OrderDetailsDaoImpl():instance;
    }
    public boolean saveOrderDetails(List<OrderDetails> orderDetails) throws SQLException {
        for(OrderDetails orderDetail : orderDetails){
           boolean isSaved = saveOrderDetails(orderDetail);
            System.out.println("OrderDetailsDaoImpl"+orderDetail);
            if(!isSaved){
                return  false;
            }
        }
        return true;
    }
    public boolean saveOrderDetails(OrderDetails orderDetails) throws SQLException {
        return CrudUtil.execute("INSERT INTO orderdetail VALUES(?,?,?,?)",
                orderDetails.getOrderId(),
                orderDetails.getItemId(),
                orderDetails.getQty(),
                orderDetails.getUnitPrice());
    }


}
