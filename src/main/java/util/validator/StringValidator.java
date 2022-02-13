package util.validator;

public class StringValidator {

    public static boolean validateVideoId(String videoId){
        if(videoId != null && !(videoId.trim().isEmpty())){
            return true;
        }
        return false;
    }

    public static boolean validateArgument(String arg){
        if(arg.length() >= 1 && StringValidator.validateVideoId(arg)){
            return true;
        }
        return false;
    }
}
