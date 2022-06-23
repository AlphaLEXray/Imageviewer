package imageviewer;

public class StringHandler {
	
	public String[] splitString(String pInput) {
		// source:
		// https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
		String[] commands = pInput.split("[|]");
		return (commands);
	}

}
