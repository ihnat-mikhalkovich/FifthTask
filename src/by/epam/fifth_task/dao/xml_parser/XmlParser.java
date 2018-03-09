package by.epam.fifth_task.dao.xml_parser;

import java.util.List;

import by.epam.fifth_task.entity.Book;

public interface XmlParser {
	
	int DEFAULT_ELEMENTS_PER_PAGE = 4;
	
	void setPageNumber(int pageNumber);
	
	void buildPage(int pageNumber);
	
	List<Book> getBooksPage();
	
	boolean setElementsPerPage(int elementsPerPage);
	
	int getElementsPerPage();
	
	int getPageAmount();
	
	void setDefaultAmountElementsPerPage();
	
	void setPathOfSourceFile(String sourceFile);
	
}
