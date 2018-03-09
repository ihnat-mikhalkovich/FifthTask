package by.epam.fifth_task.dao.xml_parser.impl;

public enum BookEnum {

    CATALOG("catalog"),
    BOOK("book"),
    ID("id"),
    AUTHOR("author"),
    TITLE("title"),
    GENRE("genre"),
    PRICE("price"),
    PUBLISH_DATE("publish_date");

    private String value;

    private BookEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
