package repository.custom.impl;

import model.Customer;
import repository.custom.CustomerDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CustomerDaoImpl implements CustomerDao {
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> customerlist = new ArrayList<>();
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer");
        while (rs.next()) {
            Customer customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), Double.parseDouble(rs.getString(4)));
            customerlist.add(customer);
        }
        return customerlist;
    }

    @Override
    public boolean save(Customer customer) throws SQLException {
            return  CrudUtil.execute("Insert Into customer Values(?,?,?,?)",customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
    }

    @Override
    public Customer searchId(String id) throws SQLException {
        return CrudUtil.execute("UPDATE customer SET name = ?, address = ?,salary = ? WHERE id =?",id);
    }

    @Override
    public boolean remove(String id) throws SQLException {
       return CrudUtil.execute("DELETE FROM Customer WHERE id = ?",id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return CrudUtil.execute("UPDATE customer SET name = ?, address = ?,salary = ? WHERE id = ?",customer.getName(),customer.getAddress(),customer.getSalary(),customer.getId());
    }
}
