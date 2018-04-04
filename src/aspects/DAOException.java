package aspects;

public class DAOException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public DAOException(String message) {
		this.message = message;
	}
}
