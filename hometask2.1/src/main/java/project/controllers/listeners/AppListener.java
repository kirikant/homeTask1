package project.controllers.listeners;

import project.storages.StorageFactory;
import project.utils.DBInitializer;
import project.utils.EntityFactoryInitializer;

import javax.persistence.Entity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        StorageFactory storageFactory = StorageFactory.getStorageFactory();
       // storageFactory.setStorage("DB");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBInitializer instance = DBInitializer.getInstance();
        EntityFactoryInitializer entityFactoryInitializer = EntityFactoryInitializer.getInstance();
        try {
            instance.close();
            entityFactoryInitializer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
