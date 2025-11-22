package repository.custom.impl;

import model.Sales;
import model.SalesCharts;
import repository.custom.SalesDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDaoImpl implements SalesDao {

    @Override
    public List<SalesCharts> getSalesChartRecord() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT month, SUM(totalAmount)/1000 AS salesInThousands FROM sales WHERE year = 2024 GROUP BY month, MONTH(saleDate) ORDER BY MONTH(saleDate)");
        ArrayList<SalesCharts> listSalesCharts = new ArrayList<>();
        while (rs.next()){
             listSalesCharts.add(new SalesCharts(rs.getString("month"), rs.getDouble("salesInThousands")));
        }
        return listSalesCharts;
    }

    @Override
    public Double getRevenue() throws SQLException {
        Double monthlyTotal = 0.0;
        ResultSet resultSet = CrudUtil.execute(
                "SELECT SUM(totalAmount) AS thisMonthTotal FROM sales WHERE month = DATE_FORMAT(CURDATE(), '%b') AND year = YEAR(CURDATE())"
        );
        if (resultSet.next()) {
            monthlyTotal = resultSet.getDouble("thisMonthTotal");
        }

        return monthlyTotal;
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
