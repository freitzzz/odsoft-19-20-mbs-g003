package pt.isep.cms.teachers.shared;

public enum Gender {
	
	MALE,
	FEMALE;
	
	public static Gender parse(String genderAsString) {
		
		if(genderAsString == null) {
			throw new IllegalArgumentException("gender cannot be null");
		}
		
		switch (genderAsString.toLowerCase()) {
		
			case "male":
				return MALE;
				
			case "female":
				return FEMALE;
				
			default:
				throw new IllegalArgumentException("gender can only be male or female");
		}
		
	}
}
