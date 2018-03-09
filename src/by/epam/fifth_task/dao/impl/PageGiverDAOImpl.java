package by.epam.fifth_task.dao.impl;

import java.util.List;

import by.epam.fifth_task.dao.PageGiverDAO;
import by.epam.fifth_task.dao.xml_parser.XmlParser;
import by.epam.fifth_task.dao.xml_parser.XmlParserDirector;
import by.epam.fifth_task.dao.xml_parser.XmlParserType;
import by.epam.fifth_task.entity.Book;

public class PageGiverDAOImpl implements PageGiverDAO {
	
	private String sourceFile = "C:\\Users\\HOME\\eclipse-workspace\\FifthTask\\WebContent\\WEB-INF\\resources\\books\\books.xml";
	
	private XmlParser xmlParser;
	
	@Override
	public List<Book> getPage(int pageNumber) {
		if (xmlParser == null) {
			return null;
		}
		xmlParser.buildPage(pageNumber);
		return xmlParser.getBooksPage();
	}
	
	@Override
	public boolean setElementsPerPage(int elementsPerPage) {
		if (xmlParser == null) {
			return false;
		}
		return xmlParser.setElementsPerPage(elementsPerPage);
	}
	
	@Override
	public int getElementsPerPage() {
		if (xmlParser == null) {
			return -1;
		}
		return xmlParser.getElementsPerPage();
	}
	
	@Override
	public int getPageAmount() {
		if (xmlParser == null) {
			return -1;
		}
		return xmlParser.getPageAmount();
	}
	
	@Override
	public boolean setParserType(String parserType) {
		XmlParserDirector director = XmlParserDirector.getInstance();
		XmlParserType enumParserType = XmlParserType.valueOf(parserType);
		xmlParser = director.getXmlParser(enumParserType);
		xmlParser.setPathOfSourceFile(sourceFile);
		return true; // можно убрать булеан
	}
}
