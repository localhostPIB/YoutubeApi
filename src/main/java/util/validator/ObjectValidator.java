package util.validator;

import model.interfaces.IVideoInfo;

public class ObjectValidator {

    public static boolean validateVideoInfoObject(final IVideoInfo iVideoInfo){
        if(iVideoInfo != null){
            return true;
        }else{
            return false;
        }
    }
}
