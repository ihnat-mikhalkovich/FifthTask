package by.epam.fifth_task.dao.xml_parser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.fifth_task.dao.xml_parser.XmlParser;
import by.epam.fifth_task.entity.Book;

public class DOMXmlParser implements XmlParser {
	
	private String sourceFile;

	private int pageNumber;
	
	private DocumentBuilder documentBuilder;
	
	private int elementsPerPage = XmlParser.DEFAULT_ELEMENTS_PER_PAGE;
	
	private int pageAmount;
	
	private List<Book> books;
	
	public DOMXmlParser(String sourceFile) {
		this();
		this.sourceFile = sourceFile;
	}
	
	public DOMXmlParser() {
		books = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;		
	}

	@Override
	public void buildPage(int selectedPage) {
		pageNumber = selectedPage;
		books.clear();
		int firstElement = (pageNumber - 1) * elementsPerPage;
        int lastElement = firstElement + elementsPerPage;
        Document document = null;
        try {
            document = documentBuilder.parse(sourceFile);
            Element root = document.getDocumentElement();
            NodeList booksList = root.getElementsByTagName("book"); //change it
            pageAmount = (int) Math.ceil((double) booksList.getLength()/elementsPerPage);
            for (int i = 0; i < booksList.getLength(); i++) {
                Element bookElement = (Element) booksList.item(i);
                if (i >= firstElement && i < lastElement) {
                    Book book = buildBook(bookElement);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
	}
	
	private Book buildBook(Element bookElement) {
        Book book = new Book();
        if (bookElement.hasAttribute("id")) {
            book.setId(bookElement.getAttribute("id"));
        }
        book.setAuthor(getElementTextContent(bookElement, "author"));
        book.setTitle(getElementTextContent(bookElement, "title"));
        book.setGenre(getElementTextContent(bookElement, "genre"));
        Double price = Double.parseDouble(getElementTextContent(bookElement, "price"));
        book.setPrice(price);
        book.setPublish_date(getElementTextContent(bookElement, "publish_date"));
        return book;
    }
	
	private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
	
	@Override
	public List<Book> getBooksPage() {
		return books;
	}

	@Override
	public boolean setElementsPerPage(int elementsPerPage) {
		this.elementsPerPage = elementsPerPage;
		return false;
	}

	@Override
	public int getElementsPerPage() {
		return elementsPerPage;
	}

	@Override
	public int getPageAmount() {
		return pageAmount;
	}
	
	@Override
	public void setDefaultAmountElementsPerPage() {
		elementsPerPage = XmlParser.DEFAULT_ELEMENTS_PER_PAGE;
	}
	
	@Override
	public void setPathOfSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

}
