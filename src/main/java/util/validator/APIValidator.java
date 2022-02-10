package util.validator;

public class APIValidator {
    public static boolean checkNextPageToken(String nextPageToken){
            if (nextPageToken == null) {
                return true;
            }
            return false;
    }
}
