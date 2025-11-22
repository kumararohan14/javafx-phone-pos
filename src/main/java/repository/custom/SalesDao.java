package repository.custom;

import model.Sales;
import model.SalesCharts;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface SalesDao extends CrudRepository<Sales,String> {
    List<SalesCharts> getSalesChartRecord() throws SQLException;
    Double getRevenue() throws SQLException;

}
