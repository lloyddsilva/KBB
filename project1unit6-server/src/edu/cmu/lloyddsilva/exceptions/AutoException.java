package edu.cmu.lloyddsilva.exceptions;

//@Author: Lloyd D'Silva / ldsilva@andrew.cmu.edu
public class AutoException extends Exception {

	private static final long serialVersionUID = -1762057850542260437L;

	private String message = "Auto Exception";
	private ErrorCodes errorCode;
	
	public AutoException() {
		super();
	}
	
	public AutoException(String message) {
		super(message);
		this.message = message;
	}
	
	public AutoException(ErrorCodes errorCode) {
		super(errorCode.toString());
		this.errorCode = errorCode;
		this.message = errorCode.toString();
		System.out.println("AutoException::"+ message);
	}
	
	@Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
    
    public int getErrorCode() {
    	return errorCode.getCode();
    }
    
    public String getErrorDescription() {
    	return errorCode.getDescription();
    }
}
