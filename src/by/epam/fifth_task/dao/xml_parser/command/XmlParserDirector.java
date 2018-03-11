package by.epam.fifth_task.dao.xml_parser.command;

import java.util.EnumMap;
import java.util.Map;

import by.epam.fifth_task.dao.xml_parser.XmlParser;
import by.epam.fifth_task.dao.xml_parser.XmlParserTypeEnum;

public class XmlParserDirector {
	
private static final XmlParserDirector instance = new XmlParserDirector();
	
	private Map<XmlParserTypeEnum, Command> xmlParserMap = new EnumMap<>(XmlParserTypeEnum.class);	
	
	private XmlParserDirector() {
		xmlParserMap.put(XmlParserTypeEnum.DOM, new DOMXmlParserCreator());
		xmlParserMap.put(XmlParserTypeEnum.SAX, new SAXXmlParserCreator());
		xmlParserMap.put(XmlParserTypeEnum.StAX, new StAXXmlParserCreator());
	}
	
	public static XmlParserDirector getInstance() {
		return instance;
	}
	
	public XmlParser getXmlParser(XmlParserTypeEnum parserType) {
		Command command = xmlParserMap.get(parserType);
		return command.execute();
	}
	
}
