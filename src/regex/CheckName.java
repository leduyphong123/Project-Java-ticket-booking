package regex;

import constType.ConstRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckName {
    private static boolean isNameRegex(String validate,String type){
        Pattern pattern = Pattern.compile(type);
        Matcher matcher = pattern.matcher(validate);
        return matcher.matches();
    }
    public static boolean isName(String validate,String type){
        if (validate.length()==0){
            return false;
        }
        if (!isNameRegex(validate,type)){
            return false;
        }
        return true;
    }
}
