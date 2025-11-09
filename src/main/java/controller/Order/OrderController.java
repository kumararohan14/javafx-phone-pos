//package controller.Order;
//
//import model.Order;
//import service.ServiceFactory;
//import service.SuperService;
//import util.DBConnection;
//import util.ServiceType;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//
//public class OrderController {
//    Itemservice ItemService = ServiceFactory.getInstance().getServiceFactoryType(ServiceType.ITEM);
//    public boolean placeOrder(Order order) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "Insert Into orders Values(?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setObject(1,order.getId());
//        preparedStatement.setObject(1,order.getDate());
//        preparedStatement.setObject(1,order.getCustId());
//        boolean isAdded = (preparedStatement.executeUpdate() > 0);
//        if (isAdded){
//            boolean isOrderDetailAdded = new OrderDeatailController().addOrderDetails(order.getOrderDtails());
//            if (isOrderDetailAdded){
//
//            }
//        }
//    }
//}
