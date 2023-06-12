package service;

import java.io.File;

public class FileHandleService {
    public static boolean isFileEmtry(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                return false;
            }

        }
        return true;
    }
}
