package service;

import entity.Storage;

public interface StorageService {
    boolean saveStorage(Storage storage);

    int getStorageId();

    long getValumeByIdFlight(int idFlight);
}
