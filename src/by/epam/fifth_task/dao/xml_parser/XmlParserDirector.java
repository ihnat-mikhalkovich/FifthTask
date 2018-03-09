package by.epam.fifth_task.dao.xml_parser;

import java.util.EnumMap;
import java.util.Map;

import by.epam.fifth_task.dao.xml_parser.impl.DOMXmlParser;
import by.epam.fifth_task.dao.xml_parser.impl.SAXXmlParser;
import by.epam.fifth_task.dao.xml_parser.impl.StAXXmlParser;

public class XmlParserDirector {
	
private static final XmlParserDirector instance = new XmlParserDirector();
	
	private Map<XmlParserType, XmlParser> xmlParserMap = new EnumMap<>(XmlParserType.class);	
	
	private XmlParserDirector() {
		xmlParserMap.put(XmlParserType.DOM, new DOMXmlParser());
		xmlParserMap.put(XmlParserType.SAX, new SAXXmlParser());
		xmlParserMap.put(XmlParserType.StAX, new StAXXmlParser());
	}
	
	public static XmlParserDirector getInstance() {
		return instance;
	}
	
	public XmlParser getXmlParser(XmlParserType parserType) {
		return xmlParserMap.get(parserType);
	}
	
}
