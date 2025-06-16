//Expcetion class to handle empty input into textfields
public class NullInputException extends Exception{
    
    public NullInputException(){
        super("One or more inputs not filled out. Please fill out all information.");
    }
    public NullInputException(String input){
        super("No value was entered for the " + input + " dialog box.\nPlease enter a valid input.");
    }
}
