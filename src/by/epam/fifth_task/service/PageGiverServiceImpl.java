package by.epam.fifth_task.service;

import java.util.List;

import by.epam.fifth_task.dao.DAOFactory;
import by.epam.fifth_task.dao.PageGiverDAO;
import by.epam.fifth_task.entity.Book;

public class PageGiverServiceImpl implements PageGiverService {
	
	@Override
	public List<Book> getPage(int pageNumber) {
		if (!Validator.pageNumberValidation(pageNumber)) {
			return null;
		}
		DAOFactory factory = DAOFactory.getInstance();
		PageGiverDAO pageGiver = factory.getPageGiverDAO();
		return pageGiver.getPage(pageNumber);
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
		return pageGiver.setParserType(parserType);
	}
	
}
