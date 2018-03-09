package by.epam.fifth_task.service;

public class Validator {
	
	public static boolean pageNumberValidation(int pageNumber) {
		return pageNumber > 0;
	}
	
	public static boolean elementsPerPageValidation(int elementsPerPage) {
		return elementsPerPage > 0;
	}
	
	public static boolean parserTypeValidation(String parserType) {
		return "SAX StAX DOM".contains(parserType);
	}
	
}
