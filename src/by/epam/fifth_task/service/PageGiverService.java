package by.epam.fifth_task.service;

import java.util.List;

import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.ServiceException;

public interface PageGiverService {
	
	List<Book> getPage(int pageNumber) throws ServiceException;
	
	boolean setElementsPerPage(int elementsPerPage);
	
	int getElementsPerPage();
	
	int getPageAmount();
	
	boolean setParserType(String parserType);
	
}
