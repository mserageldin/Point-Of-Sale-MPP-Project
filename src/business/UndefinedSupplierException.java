package business;

public class UndefinedSupplierException extends Exception {
	static final String message = "Supplier is not exist in suppliers List";
    public UndefinedSupplierException(String msg){
        super((msg == null)? message : msg);
    }
    public UndefinedSupplierException(Exception e){
        super(e);
    }
	private static final long serialVersionUID = 1234567L;	
}
