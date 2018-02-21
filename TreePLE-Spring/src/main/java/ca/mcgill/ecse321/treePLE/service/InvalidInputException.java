package ca.mcgill.ecse321.treePLE.service;

public class InvalidInputException extends Throwable {
	private static final long serialVersionUID = -5633915762703837868L;
	
	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
}
