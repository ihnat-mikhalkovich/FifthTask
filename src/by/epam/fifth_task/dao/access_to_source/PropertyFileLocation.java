package by.epam.fifth_task.dao.access_to_source;

public class PropertyFileLocation {
	
	private static final PropertyFileLocation instance = new PropertyFileLocation();
	
	private final String sourceFileLocation = "C:\\Users\\HOME\\eclipse-workspace\\FifthTask\\WebContent\\WEB-INF\\property\\property.txt";
	
	private PropertyFileLocation() {}
	
	public static PropertyFileLocation getInstance() {
		return instance;
	}
	
	public String getSourceFileLocation() {
		return sourceFileLocation;
	}
	
}
