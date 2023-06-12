package service.impl;

import constType.ConstTypeProject;
import entity.Chair;
import service.ChairService;
import service.FileHandleService;
import service.IdDefaultHandle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ChairServiceImpl implements ChairService {
    private static Chair chair;
    @Override
    public boolean saveChair(Chair chair) {
        this.chair=chair;
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR_DEFAULT)){
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_CHAIR_DEFAULT, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(chair.getId()));
            bw.write(",");
            bw.write(String.valueOf(chair.getIdFlight()));
            bw.write(",");
            bw.write(String.valueOf(chair.getLineQuantity()));
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
    public Chair getChair() {
        return chair;
    }

    @Override
    public int getChairId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_CHAIR_ID_DEFAULT)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_CHAIR_ID_DEFAULT).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_CHAIR_ID_DEFAULT);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_CHAIR_ID_DEFAULT);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }


}
