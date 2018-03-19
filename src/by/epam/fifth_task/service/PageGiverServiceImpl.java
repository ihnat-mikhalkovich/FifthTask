package by.epam.fifth_task.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.epam.fifth_task.dao.DAOFactory;
import by.epam.fifth_task.dao.PageGiverDAO;
import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.DAOException;
import by.epam.fifth_task.exception.ServiceException;

public class PageGiverServiceImpl implements PageGiverService {
	
	private static final Logger serviceLogger = Logger.getLogger("by.epam.fifth_task.service"); 
	
	@Override
	public List<Book> getPage(int pageNumber) throws ServiceException {
		if (!Validator.pageNumberValidation(pageNumber)) {
			return null;
		}
		try {
			DAOFactory factory = DAOFactory.getInstance();
			PageGiverDAO pageGiver = factory.getPageGiverDAO();
			return pageGiver.getPage(pageNumber);
		} catch (DAOException e) {
			serviceLogger.setLevel(Level.SEVERE);
			FileHandler fileHandler = null;
			try {
				fileHandler = new FileHandler("/FifthTask/WebContent/WEB-INF/log/serviceLog01.log", true);
				serviceLogger.addHandler(fileHandler);
				serviceLogger.log(Level.SEVERE, "sevice class got a DAOException.", e);
			} catch (IOException ex) {
				Logger.getGlobal().warning("serviceLog01.log file is bed.");
			} finally {
				fileHandler.close();
			}
			throw new ServiceException("sevice class got a DAOException.", e);
		}
	}
	
	@Override
	public boolean setElementsPerPage(int elementsPerPage) {
		if (!Validator.elementsPerPageValidation(elementsPerPage)) {
			return false;
		}
		DAOFactory factory = DAOFactory.getInstance();
		PageGiverDAO pageGiver = factory.getPageGiverDAO();
		return pageGiver.setElementsPerPage(elementsPerPage);
	}
	
	@Override
	public int getElementsPerPage() {
		DAOFactory factory = DAOFactory.getInstance();
		PageGiverDAO pageGiver = factory.getPageGiverDAO();
		return pageGiver.getElementsPerPage();
	}
	
	@Override
	public int getPageAmount() {
		DAOFactory factory = DAOFactory.getInstance();
		PageGiverDAO pageGiver = factory.getPageGiverDAO();
		return pageGiver.getPageAmount();
	}
	
	@Override
	public boolean setParserType(String parserType) {
		if (!Validator.parserTypeValidation(parserType)) {
			return false;
		}
		DAOFactory factory = DAOFactory.getInstance();
		PageGiverDAO pageGiver = factory.getPageGiverDAO();
		pageGiver.setParserType(parserType);
		return true;
	}
	
}
