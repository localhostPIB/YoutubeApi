package util;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static void writeInPropertyFile(final String value) {
        FileUtils.createDirectory("res");

        try (OutputStream output = new FileOutputStream("res/config.properties")) {

            Properties properties = new Properties();

            properties.setProperty("CLIENT.SECRET", value);
            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static String readPropertyFile() throws IOException {
            Properties properties = new Properties();
            properties.load(new FileReader("res/config.properties"));

            return properties.getProperty("CLIENT.SECRET");
        }
}

