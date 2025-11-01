package service.custom.impl;

import model.Customer;
import model.Item;
import repository.DaoFactory;
import repository.SuperDao;
import repository.custom.ItemDao;
import service.custom.ItemService;
import util.ServiceType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    ItemDao itemDao = DaoFactory.getInstance().getDaoFactoryType(ServiceType.ITEM);
    @Override
    public boolean save(Item item) throws SQLException {
        return itemDao.save(item);
    }

    @Override
    public boolean update(Item item) throws SQLException {
       return itemDao.update(item);
    }

    @Override
    public boolean remove(String id) throws SQLException {
       return itemDao.remove(id);
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return itemDao.getAll();
    }

    @Override
    public Item searchById(String id) throws SQLException {
        return itemDao.searchId(id);
    }
    public List<String> getItemIds() throws SQLException {
        ArrayList<String> itemIds = new ArrayList<>();
            getAll().forEach(item -> {
                itemIds.add(item.getCode());
            });
            return itemIds;
    }

    @Override
    public List<String> getItemCategories() throws SQLException {
        ArrayList<String> category = new ArrayList<>();
        getAll().forEach(item -> {
            category.add(item.getCategory());
        });
        return category;
    }

    @Override
    public List<String> getSupplierIds() throws SQLException {
        ArrayList<String> supplierIds = new ArrayList<>();
        getAll().forEach(item -> {
            supplierIds.add(item.getSupplierId());
        });
        return supplierIds;
    }
}
