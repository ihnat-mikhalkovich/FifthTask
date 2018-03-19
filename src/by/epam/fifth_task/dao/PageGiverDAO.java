package by.epam.fifth_task.dao;

import java.util.List;

import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.DAOException;

public interface PageGiverDAO {
	
	List<Book> getPage(int pageNumber) throws DAOException ;
	
	boolean setElementsPerPage(int elementsPerPage);
	
	int getElementsPerPage();
	
	int getPageAmount();
	
	void setParserType(String parserType);
	
}
