package by.epam.fifth_task.dao.xml_parser;

import java.util.List;

import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.DAOException;

public interface XmlParser {
	
	int DEFAULT_ELEMENTS_PER_PAGE = 4;
		
	void buildPage(int pageNumber) throws DAOException;
	
	List<Book> getBooksPage();
	
	boolean setElementsPerPage(int elementsPerPage);
	
	int getElementsPerPage();
	
	int getPageAmount();
	
	void setDefaultAmountElementsPerPage();
	
	void setPathOfSourceFile(String sourceFile);
	
}
