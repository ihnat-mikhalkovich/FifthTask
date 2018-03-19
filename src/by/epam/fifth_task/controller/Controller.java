package by.epam.fifth_task.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.fifth_task.business_logic.Pagination;
import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.ServiceException;
import by.epam.fifth_task.service.PageGiverService;
import by.epam.fifth_task.service.ServiceFactory;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parserType = request.getParameter("parserType");
		String selectedPage = request.getParameter("selectedPage");
		
		ServiceFactory factory = ServiceFactory.getInstance();
		PageGiverService pageGiver = factory.getPageGiverService();
		
		pageGiver.setParserType(parserType);
		List<Book> books = null;
		try {
			books = pageGiver.getPage(Integer.parseInt(selectedPage));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		int pagesAmount = pageGiver.getPageAmount();
			
		Pagination pagination = new Pagination(pagesAmount);
		List<String> paginationList = pagination.paginating(Integer.parseInt(selectedPage));
		String intermediateSymbolsOfPagination = pagination.getIntermediateSymbols();
		
		request.setAttribute("intermediateSymbolsOfPagination", intermediateSymbolsOfPagination);
		request.setAttribute("parserType", parserType);
		request.setAttribute("paginationList", paginationList);
		request.setAttribute("books", books);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/books.jsp");
		dispatcher.forward(request, response);
	}
		
}
