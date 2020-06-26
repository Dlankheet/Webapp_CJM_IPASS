package nl.cjm.setup;

import java.util.regex.Pattern;

public class Utils {
    public static boolean checkPhone(String phone){
        return phone.matches("^\\(?([+]31|0031|0)-?6(\\s?|-)([0-9]\\s{0,3}){8}$");
    }
    public static boolean checkEmail(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }
}
