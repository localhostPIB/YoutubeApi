package util;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static String readPropertyFile() throws IOException {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            return prop.getProperty("CLIENT.SECRET");

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return "";
    }
}

