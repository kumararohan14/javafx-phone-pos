package service.custom.impl;

import model.Customer;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custom.CustomerService;
import util.ServiceType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao repositary= DaoFactory.getInstance().getDaoFactoryType(ServiceType.CUSTOMER);
public boolean save(Customer customer) throws SQLException {
    return repositary.save(customer);
}
public boolean update(Customer customer) throws SQLException {
    return repositary.update(customer);
}
public boolean remove(String id) throws SQLException {
    return repositary.remove(id);
}

public ArrayList<Customer> getAll() throws SQLException {
    return (ArrayList<Customer>) repositary.getAll();
}
public Customer searchById(String id) throws SQLException {
    return repositary.searchId(id);
}

public List<String> getIds() {
    try {
        ArrayList<Customer> all = getAll();
        List<String> custIdList = new ArrayList<>();
        all.forEach(customer -> {
            custIdList.add(customer.getId());
        });
        return custIdList;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

}
