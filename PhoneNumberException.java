//Exception class to handle exceptions for when the user enters their phone number
public class PhoneNumberException extends Exception{
    
    public PhoneNumberException(String num){
        super("Invalid Phone Number Input Format: \"" + num +"\"  .\nMake sure your phone number has 10 digits.");
    }
}
