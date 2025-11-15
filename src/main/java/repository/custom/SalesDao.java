package repository.custom;

import model.Sales;
import model.SalesCharts;
import repository.CrudRepository;

public interface SalesDao extends CrudRepository<Sales,String> {
    SalesCharts getSalesChartRecord();
}
