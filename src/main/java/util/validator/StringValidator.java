package util.validator;

public class StringValidator {

    public static boolean validateVideoId(String videoId){
        if(videoId != null || !(videoId.trim().isEmpty())){
            return true;
        }
        return false;
    }
}
