package by.epam.fifth_task.dao;

import java.util.List;

import by.epam.fifth_task.entity.Book;

public interface PageGiverDAO {
	
	List<Book> getPage(int pageNumber);
	
	boolean setElementsPerPage(int elementsPerPage);
	
	int getElementsPerPage();
	
	int getPageAmount();
	
	boolean setParserType(String parserType);
	
}
