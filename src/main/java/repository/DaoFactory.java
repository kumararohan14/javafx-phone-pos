package repository;

import repository.custom.impl.CustomerDaoImpl;
import repository.custom.impl.ItemDaoImpl;
import util.ServiceType;

import static com.sun.java.accessibility.util.EventID.ITEM;
import static util.ServiceType.CUSTOMER;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){
    }
    public static DaoFactory getInstance(){
        return instance==null?instance=new DaoFactory():instance;
    }
    public <T extends SuperDao>T getDaoFactoryType(ServiceType type){
        switch (type){
            case CUSTOMER : return (T) new CustomerDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();
        }
        return null;
    }
}
