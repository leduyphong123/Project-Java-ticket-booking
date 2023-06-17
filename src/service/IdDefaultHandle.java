package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class IdDefaultHandle {
    public static int getMaxIdDefault(List<Integer> listIdDefault) {
        int max = listIdDefault.get(0);
        for (int i = 1; i < listIdDefault.size(); i++) {
            if (max < listIdDefault.get(i)) {
                max = listIdDefault.get(i);
            }
        }
        return max;
    }

    public static boolean writeIdDefault(int id,String path) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(id));
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

    public static List<Integer> readIdDefault(String type) {
        FileReader fr = null;
        BufferedReader br = null;
        List<Integer> listChairId = new ArrayList<>();
        try {
            fr = new FileReader(type);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                listChairId.add(Integer.valueOf(line));
            }
        } catch (Exception e) {
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
        return listChairId;
    }

}
