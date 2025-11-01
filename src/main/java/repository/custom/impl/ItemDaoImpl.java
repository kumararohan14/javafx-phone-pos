package repository.custom.impl;

import model.Item;
import repository.custom.ItemDao;
import util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public ArrayList<Item> getAll() throws SQLException {
        ArrayList<Item> itemArrayLists = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM items");

                while (resultSet.next()) {
                    Item item = new Item(
                            resultSet.getString(1),   // code
                            resultSet.getString(2),   // description
                            resultSet.getString(3),   // category
                            resultSet.getString(4),   // brand
                            resultSet.getString(5),   // supplierId
                            resultSet.getDouble(6),   // costPrice
                            resultSet.getDouble(7),   // unitPrice
                            resultSet.getInt(8),      // qtyOnHand
                            resultSet.getInt(9),      // reorderLevel
                            resultSet.getString(10),  // location
                            resultSet.getDate(11).toLocalDate(), // productionDate
                            resultSet.getString(12),  // warrantyPeriod
                            resultSet.getString(13)   // status
                    );
                    itemArrayLists.add(item);
                }
        return itemArrayLists;
    }

    @Override
    public boolean save(Item item) throws SQLException {
        Boolean saved = CrudUtil.execute( "INSERT INTO items VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)",
                item.getCode(),
                item.getDescription(),
                item.getCategory(),
                item.getBrand(),
                item.getSupplierId(),
                item.getCostPrice(),
                item.getUnitPrice(),
                item.getQtyOnHand(),
                item.getReorderLevel(),
                item.getLocation(),
                item.getProductionDate(),
                item.getWarrantyPeriod(),
                item.getStatus());
        return saved;
    }

    @Override
    public Item searchId(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM items WHERE code=?", id);
        resultSet.next();

        Item item = new Item(
                resultSet.getString(1),   // code
                resultSet.getString(2),   // description
                resultSet.getString(3),   // category
                resultSet.getString(4),   // brand
                resultSet.getString(5),   // supplierId
                resultSet.getDouble(6),   // costPrice
                resultSet.getDouble(7),   // unitPrice
                resultSet.getInt(8),      // qtyOnHand
                resultSet.getInt(9),      // reorderLevel
                resultSet.getString(10),  // location
                resultSet.getDate(11).toLocalDate(), // productionDate
                resultSet.getString(12),  // warrantyPeriod
                resultSet.getString(13)   // status
        );

        return item;
    }

    @Override
    public boolean remove(String id) throws SQLException {
        Boolean deleted = CrudUtil.execute("DELETE FROM items WHERE code = ?", id);
        return deleted;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        Boolean updated = CrudUtil.execute(
                "UPDATE items SET description = ?, category = ?, brand = ?, supplierId = ?, costPrice = ?, unitPrice = ?, qtyOnHand = ?, reorderLevel = ?, location = ?, productionDate = ?, warrantyPeriod = ?, status = ? WHERE code = ?",
                item.getDescription(),
                item.getCategory(),
                item.getBrand(),
                item.getSupplierId(),
                item.getCostPrice(),
                item.getUnitPrice(),
                item.getQtyOnHand(),
                item.getReorderLevel(),
                item.getLocation(),
                item.getProductionDate(),
                item.getWarrantyPeriod(),
                item.getStatus(),
                item.getCode()  // WHERE condition comes last
        );
        return updated;
    }
}
