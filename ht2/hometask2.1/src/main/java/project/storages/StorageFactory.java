package project.storages;

import project.storages.api.IChatStorageWithAudit;
import project.storages.db.DBChatStorageWithAudit;
import project.storages.hib.HibChatStorageWithAudit;

public class StorageFactory {

    private  static  StorageFactory storageFactory = new StorageFactory();
    private String storage="HIB";

    private StorageFactory() {
    }

    public IChatStorageWithAudit getStorage()  {
        IChatStorageWithAudit storage1= null;
        switch (storage){
            case "DB":
                storage1= DBChatStorageWithAudit.getDBChatStorageWithAudit();
                break;
            case "HIB":
                storage1= HibChatStorageWithAudit.getHibChatStorageWithAudit();
                break;
        }
        return storage1;
    }

    public static StorageFactory getStorageFactory() {
        return storageFactory;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
