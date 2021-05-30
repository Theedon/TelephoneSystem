package sample;

public class ValidateInput {

    public static boolean ValidateEmail(String email){
        boolean isValidatedEmail= false;
        String regex= "^(.+)@(.+)$";
        if(email.matches(regex)){
            isValidatedEmail= true;
        }
        return isValidatedEmail;
    }

    public static boolean ValidatePhone(String phone){
        boolean isValidatedPhone= false;
        String regex= "[0-9]+";
        if(phone.matches(regex)){
            isValidatedPhone= true;
        }
        return isValidatedPhone;
    }
}
