package repository.custom.impl;

import model.Sales;
import model.SalesCharts;
import repository.custom.SalesDao;

import java.sql.SQLException;
import java.util.List;

public class SalesDaoImpl implements SalesDao {

    @Override
    public SalesCharts getSalesChartRecord() {
        return null;
    }

    @Override
    public List<Sales> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Sales sales) throws SQLException {
        return false;
    }

    @Override
    public Sales searchId(String s) throws SQLException {
        return null;
    }

    @Override
    public boolean remove(String s) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Sales sales) throws SQLException {
        return false;
    }
}
