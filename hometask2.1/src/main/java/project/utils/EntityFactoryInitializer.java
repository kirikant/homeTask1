package project.utils;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EntityFactoryInitializer implements  AutoCloseable {
    private volatile static EntityFactoryInitializer instance;
    private  static EntityManagerFactory emFactory;



    private EntityFactoryInitializer()  {
        emFactory = Persistence.createEntityManagerFactory("project1");
    }

    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static EntityFactoryInitializer getInstance() {
        if(instance == null){
            synchronized (EntityFactoryInitializer.class){
                if(instance == null){
                    try{
                        instance = new EntityFactoryInitializer();
                    } catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void close() throws Exception {
        if (emFactory.isOpen()){
            emFactory.close();
        }
    }
}
