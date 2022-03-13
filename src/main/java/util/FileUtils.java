package util;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {
    private static final String CURRENTPROGRAMPATH = Paths.get("").toAbsolutePath().toString();

    public static File createDirectory(String dirName){
        File dir = new File(CURRENTPROGRAMPATH,dirName);

        if(!dir.exists()) {
            dir.mkdir();
        }

        return dir;
    }

    public static boolean isFileExists(String path){
        File file = new File(path);

        if(file.exists() && !file.isDirectory()) {
            return true;
        }

        return false;
    }
}
