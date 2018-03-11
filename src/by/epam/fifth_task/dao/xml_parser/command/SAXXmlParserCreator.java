package by.epam.fifth_task.dao.xml_parser.command;

import by.epam.fifth_task.dao.xml_parser.impl.SAXXmlParser;

public class SAXXmlParserCreator implements Command {
	
	public SAXXmlParser execute() {
		return new SAXXmlParser();
	}
	
}
