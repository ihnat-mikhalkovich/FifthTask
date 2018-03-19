package by.epam.fifth_task.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.epam.fifth_task.dao.PageGiverDAO;
import by.epam.fifth_task.dao.access_to_source.PropertyFileReader;
import by.epam.fifth_task.dao.access_to_source.SourceTypeEnum;
import by.epam.fifth_task.dao.xml_parser.XmlParser;
import by.epam.fifth_task.dao.xml_parser.XmlParserTypeEnum;
import by.epam.fifth_task.dao.xml_parser.command.XmlParserDirector;
import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.DAOException;

public class PageGiverDAOImpl implements PageGiverDAO {
	
	private String sourceFile;
	
	private XmlParser xmlParser;
	
	private SourceTypeEnum sourceType = SourceTypeEnum.BOOKS;
	
	private static final Logger daoLogger = Logger.getLogger("com.ihnat.mikhalkovich.dao");
	
	{
		PropertyFileReader propertyReader = new PropertyFileReader();
		try {
			sourceFile = propertyReader.getPathOfSourceFile(sourceType);
		} catch (DAOException e) {
			daoLogger.setLevel(Level.SEVERE);
			FileHandler fileHandler = null;
			try {
				fileHandler = new FileHandler("/FifthTask/WebContent/WEB-INF/log/daoLog01.log", true);
		        daoLogger.addHandler(fileHandler);
		        daoLogger.log(Level.SEVERE, "Source file not found.", e);
			} catch (IOException ex) {
				Logger.getGlobal().warning("daolog01.log file is bed.");
			} finally {
				fileHandler.close();
			}
	        
		}
	}
	
	@Override
	public List<Book> getPage(int pageNumber) throws DAOException {
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
	public void setParserType(String parserType) {
		XmlParserDirector director = XmlParserDirector.getInstance();
		XmlParserTypeEnum enumParserType = XmlParserTypeEnum.valueOf(parserType);
		xmlParser = director.getXmlParser(enumParserType);
		xmlParser.setPathOfSourceFile(sourceFile);
	}
}
