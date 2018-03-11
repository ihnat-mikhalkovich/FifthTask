package by.epam.fifth_task.dao.xml_parser.command;

import by.epam.fifth_task.dao.xml_parser.impl.StAXXmlParser;

public class StAXXmlParserCreator implements Command {

	public StAXXmlParser execute() {
		return new StAXXmlParser();
	}
	
}
