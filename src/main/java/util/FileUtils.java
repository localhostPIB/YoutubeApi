package util;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {
    private static final String CURRENTPROGRAMPATH = Paths.get("").toAbsolutePath().toString();

    public static File createDirectory(){
        File dir = new File(CURRENTPROGRAMPATH,"CSVFiles");

        if(!dir.exists()) {
            dir.mkdir();
        }

        return dir;
    }
}
