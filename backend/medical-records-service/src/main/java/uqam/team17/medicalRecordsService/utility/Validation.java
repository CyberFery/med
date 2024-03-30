package uqam.team17.medicalRecordsService.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean validHealthInsuranceNumber(String healthInsuranceNumber) {
        String regex = "^[a-zA-Z]{4}\\d{8,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(healthInsuranceNumber.trim());
        return matcher.matches();
    }
}
