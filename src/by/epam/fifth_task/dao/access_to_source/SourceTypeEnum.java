package by.epam.fifth_task.dao.access_to_source;

public enum SourceTypeEnum {
	
	BOOKS("books");
	
	private SourceTypeEnum(String nameOfSource) {
		this.nameOfSource = nameOfSource;
	}
	
	private String nameOfSource;
	
	public String getNameOfSource() {
		return nameOfSource;
	}
	
}
