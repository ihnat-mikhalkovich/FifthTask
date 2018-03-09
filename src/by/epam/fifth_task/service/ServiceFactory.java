package by.epam.fifth_task.service;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final PageGiverService pageGiverService = new PageGiverServiceImpl();
	
	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public PageGiverService getPageGiverService() {
		return pageGiverService;
	}
	
}
