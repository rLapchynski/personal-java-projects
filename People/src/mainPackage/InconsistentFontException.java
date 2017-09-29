package mainPackage;

public class InconsistentFontException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public InconsistentFontException() {
		super();
	}
	public InconsistentFontException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public InconsistentFontException(String message, Throwable cause) {
		super(message, cause);
	}
	public InconsistentFontException(String message) {
		super(message);
	}
	public InconsistentFontException(Throwable cause) {
		super(cause);
	}	

}
