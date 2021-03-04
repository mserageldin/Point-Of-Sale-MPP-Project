package business;

public class InsufficientBalanceException extends Exception {
	static final String message = "Insufficent balance";
    public InsufficientBalanceException(String msg){
        super((msg == null)? message : msg);
    }
    public InsufficientBalanceException(Exception e){
        super(e);
    }
	private static final long serialVersionUID = 3258128038L;	
}
