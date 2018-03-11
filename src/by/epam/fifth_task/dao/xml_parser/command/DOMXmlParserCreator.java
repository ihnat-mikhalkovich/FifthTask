package by.epam.fifth_task.dao.xml_parser.command;

import by.epam.fifth_task.dao.xml_parser.impl.DOMXmlParser;

public class DOMXmlParserCreator implements Command {
	
	public DOMXmlParser execute() {
		return new DOMXmlParser();
	}
	
}
