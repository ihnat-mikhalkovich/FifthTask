package by.epam.fifth_task.dao;

import by.epam.fifth_task.dao.impl.PageGiverDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	
	private final PageGiverDAO pageGiverDAO = new PageGiverDAOImpl();
	
	private DAOFactory() {}

	public static DAOFactory getInstance() {
		return instance;
	}

	public PageGiverDAO getPageGiverDAO() {
		return pageGiverDAO;
	}
	
}
