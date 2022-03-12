package util;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {

    public static void writeInPropertyFile(final String value) {
        try (OutputStream output = new FileOutputStream("config.properties")) {

            Properties properties = new Properties();

            properties.setProperty("CLIENT.SECRET", value);
            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static String readPropertyFile() throws IOException {
            Properties properties = new Properties();
            properties.load(new FileReader("config.properties"));

            return properties.getProperty("CLIENT.SECRET");
        }
}

