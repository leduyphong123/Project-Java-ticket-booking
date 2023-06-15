package service.impl;

import constType.ConstTypeProject;
import entity.Storage;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.StorageService;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class StorageServiceImpl implements StorageService {
    @Override
    public boolean saveStorage(Storage storage) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE_DEFAULT)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_STORAGE_DEFAULT, true);
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
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE_ID_DEFAULT)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_STORAGE_ID_DEFAULT).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_STORAGE_ID_DEFAULT);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_STORAGE_ID_DEFAULT);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public long getValumeByIdFlight(int idFlight) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_STORAGE_DEFAULT)) {
            return -1;
        }
        long valume=-1;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_STORAGE_DEFAULT);
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
}
