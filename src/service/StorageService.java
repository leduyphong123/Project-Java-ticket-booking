package service;

import entity.Storage;

import java.util.List;

public interface StorageService {
    boolean saveStorage(Storage storage);

    int getStorageId();

    long getValumeByIdFlight(int idFlight);

    List<Storage> getAll();

    boolean isExitId(int storageId);

    boolean editStorage(int storageId, String valume);

    boolean deleteStorage(int flightId);
}
