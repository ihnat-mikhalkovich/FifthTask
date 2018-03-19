package by.epam.fifth_task.dao.xml_parser.impl;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.fifth_task.dao.xml_parser.XmlParser;
import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.DAOException;

public class SAXXmlParser implements XmlParser {
	
	private String sourceFile;

	private List<Book> books;

    private BookHandler bh;

    private int pageAmount;

    private XMLReader reader;

    private int elementsPerPage = XmlParser.DEFAULT_ELEMENTS_PER_PAGE;
	
    public SAXXmlParser() {}
	
	@Override
	public void buildPage(int pageNumber) throws DAOException {
		bh = new BookHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(bh);
        } catch (SAXException e) {
        	throw new DAOException("SAX parser is broken.", e);
        }
		try {
            reader.parse(sourceFile);
        } catch (SAXException | IOException e) {
        	throw new DAOException("SAX parser is broken.", e);
        }
        books = bh.getBooks();
        int firstBookOfPage = elementsPerPage * (pageNumber - 1);
        int lastBookOfPage = firstBookOfPage + elementsPerPage;
        pageAmount = (int) Math.ceil((double) books.size()/elementsPerPage);
        books = books.subList(firstBookOfPage, lastBookOfPage);
	}

	@Override
	public List<Book> getBooksPage() {
		return books;
	}

	@Override
	public boolean setElementsPerPage(int elementsPerPage) {
		this.elementsPerPage = elementsPerPage;
		return true;
	}

	@Override
	public int getElementsPerPage() {
		return elementsPerPage;
	}

	@Override
	public int getPageAmount() {
		return pageAmount;
	}
	
	public void setDefaultAmountElementsPerPage() {
		elementsPerPage = XmlParser.DEFAULT_ELEMENTS_PER_PAGE;
	}
	
	@Override
	public void setPathOfSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
}
