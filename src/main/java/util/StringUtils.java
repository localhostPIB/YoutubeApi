package util;

import com.google.api.client.util.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StringUtils {

    public static String cutStringBeforeChar(String s){

        return s.replaceAll(".+=", "").replaceAll("\\}","");
    }
}
