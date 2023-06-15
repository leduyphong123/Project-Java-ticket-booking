package service.impl;

import constType.ConstTypeProject;
import entity.Acount;
import entity.Flight;
import service.AcountService;
import service.FileHandleService;
import service.IdDefaultHandle;
import service.builder.AcountBuilder;
import service.builder.FlightBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class AcountServiceImpl implements AcountService {
    @Override
    public boolean saveAcount(Acount acount) {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_ACOUNT)) {
            return false;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ConstTypeProject.PATH_ACOUNT, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(acount.getId()));
            bw.write(",");
            bw.write(acount.getEmail());
            bw.write(",");
            bw.write(acount.getPassword());
            bw.write(",");
            bw.write(acount.getType());
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
    public int getAcountId() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_ACOUNT_ID)) {
            return 0;
        }
        if (IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_ACOUNT_ID).size()==0){
            IdDefaultHandle.writeIdDefault(1,ConstTypeProject.PATH_ACOUNT_ID);
            return 1;
        }
        List<Integer> listIdDefault = IdDefaultHandle.readIdDefault(ConstTypeProject.PATH_ACOUNT_ID);
        return IdDefaultHandle.getMaxIdDefault(listIdDefault);
    }

    @Override
    public boolean checkEmail(String email) {
        List<Acount> acountList=getAllAcount();
        if (acountList==null){
            return false;
        }
        for (Acount element: acountList){
            if (element.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkAcount(String email, String passworld) {
        List<Acount> acountList=getAllAcount();
        if (acountList==null){
            return false;
        }
        for (Acount element: acountList){
            if (element.getEmail().equals(email) && element.getPassword().equals(passworld)){
                return true;
            }
        }
        return false;
    }

    public List<Acount> getAllAcount() {
        if (!FileHandleService.isFileEmtry(ConstTypeProject.PATH_ACOUNT)) {
            return null;
        }
        List<Acount> acountList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(ConstTypeProject.PATH_ACOUNT);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                Acount acount = new AcountBuilder()
                        .withIdBuilder(Integer.parseInt(result[0]))
                        .withEmailBuilder(result[1])
                        .withPasswordBuilder(result[2])
                        .withTypeBuilder(result[3])
                        .builder();
                acountList.add(acount);
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
        return acountList;
    }
}