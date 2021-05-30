package sample;

public class ValidateInput {

    public static boolean ValidateEmail(String email){
        boolean isValidatedEmail= false;
        String regex= "^(.+)@(.+)$";
        if(regex.equals(email)){
            isValidatedEmail= true;
        }
        return isValidatedEmail;
    }

    public static boolean ValidatePhone(String phone){
        boolean isValidatedPhone= false;
        String regex= "^\\d{1)}$";
        if(regex.equals(phone)){
            isValidatedPhone= true;
        }
        return isValidatedPhone;
    }
}
