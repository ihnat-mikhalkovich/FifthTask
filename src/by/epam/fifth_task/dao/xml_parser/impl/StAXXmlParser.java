package by.epam.fifth_task.dao.xml_parser.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.fifth_task.dao.xml_parser.XmlParser;
import by.epam.fifth_task.entity.Book;
import by.epam.fifth_task.exception.DAOException;

public class StAXXmlParser implements XmlParser {
		
	private String sourceFile;
	
	private List<Book> books = new ArrayList<>();
	
	private int pageAmount;
	
	private XMLInputFactory inputFactory;
	
	private int elementsPerPage = XmlParser.DEFAULT_ELEMENTS_PER_PAGE;
	
	public StAXXmlParser() {
		inputFactory = XMLInputFactory.newInstance();
	}

	@Override
	public void buildPage(int pageNumber) throws DAOException {
		FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(sourceFile));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (BookEnum.valueOf(name.toUpperCase()) == BookEnum.BOOK) {
                        Book book = buildBook(reader);
                        books.add(book);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
        	throw new DAOException("StAX parser is broken.", e);
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
            	throw new DAOException("StAX parser is broken.", e);
            }
        }
        int firstBookOfPage = elementsPerPage * (pageNumber - 1);
        int lastBookOfPage = firstBookOfPage + elementsPerPage;
        pageAmount = (int) Math.ceil((double) books.size()/elementsPerPage);
        books = books.subList(firstBookOfPage, lastBookOfPage);
	}
	
	private Book buildBook(XMLStreamReader reader) throws XMLStreamException {
        Book book = new Book();
        book.setId(reader.getAttributeValue(null, BookEnum.ID.getValue()));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    BookEnum bookEnum = BookEnum.valueOf(name.toUpperCase());
                    switch (bookEnum) {
                        case AUTHOR:
                            book.setAuthor(getXMLText(reader));
                            break;
                        case TITLE:
                            book.setTitle(getXMLText(reader));
                            break;
                        case GENRE:
                            book.setGenre(getXMLText(reader));
                            break;
                        case PRICE:
                            name = getXMLText(reader);
                            book.setPrice(Double.parseDouble(name));
                            break;
                        case PUBLISH_DATE:
                            book.setPublish_date(getXMLText(reader));
                            break;
                        default:
                        	throw new XMLStreamException();
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (BookEnum.valueOf(name.toUpperCase()) == BookEnum.BOOK) {
                        return book;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
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
