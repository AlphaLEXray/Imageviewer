package imageviewer;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

public class ImageHandler {

	public ImageIcon createImageIcon(String pPath) {
		ImageIcon image = new ImageIcon(pPath);
		return (image);
	}

	// source:
	// https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
	public ImageIcon resizeImage(ImageIcon pImageIcon, int pWidth, int pHeight) {
		Image image = pImageIcon.getImage();
		Image newimage = image.getScaledInstance(pWidth, pHeight, java.awt.Image.SCALE_SMOOTH);
		pImageIcon = new ImageIcon(newimage);
		return (pImageIcon);
	}

	public ImageIcon fileToImageIcon(File pFile) {
		File imageFile = pFile;
		String path = imageFile.getAbsolutePath();
		// ImageIcon
		ImageIcon image = new ImageIcon(path);
		return (image);
	}
}
