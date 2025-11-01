package service;

import repository.custom.impl.CustomerDaoImpl;
import repository.custom.impl.ItemDaoImpl;
import service.custom.impl.CustomerServiceImpl;

import service.custom.impl.ItemServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceFactoryType(ServiceType type){
        switch (type){
            case CUSTOMER : return (T) new CustomerServiceImpl();
            case ITEM:return (T) new ItemServiceImpl();

        }
        return null;
    }
}
