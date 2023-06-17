package service.impl;

import comparator.ComparatoIdStorage;
import constType.ConstTypeProject;
import entity.Flight;
import entity.Storage;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.StorageService;
import service.builder.FlightBuilder;
import service.builder.StorageBuilder;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorageServiceImpl implements StorageService {
    @Override
    public boolean saveStorage(Storage storage) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_STORAGE, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(storage.getId()));
            bw.write(",");
            bw.write(String.valueOf(storage.getIdFlight()));
            bw.write(",");
            bw.write(String.valueOf(storage.getValume()));
            bw.newLine();
        } catch (Exception e) {
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    return false;
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int getStorageId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE_ID)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_STORAGE_ID).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_STORAGE_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_STORAGE_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public long getValumeByIdFlight(int idFlight) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE)) {
            return -1;
        }
        long valume=-1;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_STORAGE);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                if (Integer.valueOf(result[1])==idFlight){
                    valume=Long.valueOf(result[2]);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    return -1;
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    return -1;
                }
            }
        }
        return valume;
    }

    @Override
    public List<Storage> getAll() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE)) {
            return null;
        }
        List<Storage> storageList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_STORAGE);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                Storage storage = new StorageBuilder()
                        .withIdBuilder(Integer.parseInt(result[0]))
                        .withIdFlightBuilder(Integer.parseInt(result[1]))
                        .withValumeBuilder(Long.parseLong(result[2]))
                        .builder();
                storageList.add(storage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    return null;
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return storageList;
    }

    @Override
    public boolean isExitId(int storageId) {
        List<Storage> storageList = getAll();
        for (Storage element : storageList){
            if (element.getId()==storageId){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editStorage(int storageId, String valume) {
        List<Storage> storageList = getAll();
        Storage storage = new StorageBuilder()
                .withValumeBuilder(Long.parseLong(valume))
                .builder();
        int index=-1;
        for (int i=0;i<storageList.size();i++){
            if (storageList.get(i).getId()==storageId){
                storage.setId(storageList.get(i).getId());
                storage.setIdFlight(storageList.get(i).getIdFlight());
                index=i;
                break;
            }
        }
        if (index==-1){
            return false;
        }
        storageList.remove(index);
        storageList.add(storage);
        ComparatoIdStorage comparatoIdStorage = new ComparatoIdStorage();
        Collections.sort(storageList,comparatoIdStorage);
        return true;
    }

    @Override
    public boolean deleteStorage(int flightId) {
        List<Storage> storageList = getAll();

        int index=-1;
        for (int i=0;i<storageList.size();i++){
            if (storageList.get(i).getIdFlight()==flightId){
                index=i;
                break;
            }
        }
        if (index==-1){
            return false;
        }
        storageList.remove(index);
        ComparatoIdStorage comparatoIdStorage = new ComparatoIdStorage();
        Collections.sort(storageList,comparatoIdStorage);
        return true;
    }
}
