package by.epam.fifth_task.dao.access_to_source;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PropertyFileReader {
	
	private String propertyFileLocation;
	
	private String separatingCharacterOfFile = " ";
	
	{
		PropertyFileLocation propertyFileLocation = PropertyFileLocation.getInstance();
		this.propertyFileLocation = propertyFileLocation.getSourceFileLocation();
	}
	
	public String getPathOfSourceFile(SourceTypeEnum sourceType) {
		try (FileReader fileReader = new FileReader(propertyFileLocation);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String lineOfPropertyFile = null;
			while ((lineOfPropertyFile = bufferedReader.readLine()) != null) {
				String[] nameOfSourceAndHimPath = lineOfPropertyFile.split(separatingCharacterOfFile);
				String nameOfSource = nameOfSourceAndHimPath[0];
				String pathOfSource = nameOfSourceAndHimPath[1];
				if (nameOfSource.equals(sourceType.getNameOfSource())) {
					return pathOfSource;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
