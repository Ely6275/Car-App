public class NegativeInputException extends Exception{

    public NegativeInputException(int n){
        super("Negative Number \"" + n + "\" Entered.\nPlease Enter Valid Input");
    }
}