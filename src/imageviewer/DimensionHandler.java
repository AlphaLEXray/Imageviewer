package imageviewer;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DimensionHandler {
	
	public Dimension getScreenSize() {
		// Source:
		// https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return (screenSize);
	}

}
