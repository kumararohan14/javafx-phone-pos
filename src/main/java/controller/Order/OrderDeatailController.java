//package controller.Order;
//
//import model.OrderDetails;
//import util.CrudUtil;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderDeatailController {
//    public boolean addOrderDetails(List<OrderDetails> orderDtails) {
//        for(OrderDetails orderDetails:orderDtails){
//            boolean isOrderDetailsAdd = addOrderDetails(orderDetails);
//            if(!isOrderDetailsAdd){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean addOrderDetails(OrderDetails orderDetails) {
//        String sql = "INSERT INTO orderdetail VALUES (?,?,?,?)";
//        try {
//            return CrudUtil.execute(sql,orderDetails.getOrderId(),orderDetails.getItemId(),orderDetails.getQty(),orderDetails.getUnitPrice());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
