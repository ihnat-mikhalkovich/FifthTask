package by.epam.fifth_task.dao.xml_parser.impl;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.fifth_task.entity.Book;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BookHandler extends DefaultHandler {

    private List<Book> books;

    private Book current = null;

    private BookEnum currentEnum = null;

    private EnumSet<BookEnum> withText;

    public BookHandler() {
        books = new ArrayList<>();
        withText = EnumSet.range(BookEnum.AUTHOR, BookEnum.PUBLISH_DATE);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("book".equals(localName)) {
            current = new Book();
            current.setId(attributes.getValue(0));
        } else {
            BookEnum temp = BookEnum.valueOf(localName.toUpperCase());
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("book".equals(localName)) {
            books.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case AUTHOR:
                    current.setAuthor(s);
                    break;
                case TITLE:
                    current.setTitle(s);
                    break;
                case GENRE:
                    current.setGenre(s);
                    break;
                case PRICE:
                    current.setPrice(Double.parseDouble(s));
                    break;
                case PUBLISH_DATE:
                    current.setPublish_date(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }

}
