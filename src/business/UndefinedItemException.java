package business;

public class UndefinedItemException extends Exception {
	static final String message = "Item code is not exist in Items List";
    public UndefinedItemException(String msg){
        super((msg == null)? message : msg);
    }
    public UndefinedItemException(Exception e){
        super(e);
    }
	private static final long serialVersionUID = 3258128038058015026L;	
}
