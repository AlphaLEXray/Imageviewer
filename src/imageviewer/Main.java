package imageviewer;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class Main {
	
	//Returns a list of Files based on the filepath given
	public File[] ListOfFiles(String pPath) throws IOException {
	      //Creating a File object for directory
	      File directoryPath = new File(pPath);
	      //List of all files and directories
	      File filesList[] = directoryPath.listFiles();
	      return(filesList);
	}
	
	//Source: http://www.java2s.com/Code/J0ava/Swing-JFC/SelectadirectorywithaJFileChooser.htm
	public File chooseDirectory() {
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose a Directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    File chosen = null;
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        chosen = chooser.getSelectedFile();
	      }
	    return(chosen);
	}
	
	public File chooseFile() {
		FileFilter imageFileFilter = new FileNameExtensionFilter("JPEG/PNG(.png, .jpeg)", "png", "jpeg");
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(imageFileFilter);
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose a File");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    File chosen = null;
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        chosen = chooser.getSelectedFile();
	      }  else {
	    	  System.exit(0);
		      }
	    return(chosen);
	}
	
	public void showImage() throws IOException {	
		//File chooser
		File imageFile = chooseFile(); //chooses files
		String path = imageFile.getAbsolutePath();

		//ImageIcon
		ImageIcon image = new ImageIcon(path);
		
		//Source: https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();	
		
		//JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer v.1.0");
		frame.setVisible(true);
		frame.setSize(width, height);
		
		//JLabel
		JLabel label = new JLabel();
		label.setIcon(image);
		
		//Add label to frame
		frame.add(label);
	}
	
	public JFrame createFrame(String pPath, String pVersion) {
		
		//Source: https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		ImageIcon image = new ImageIcon(pPath);
		
		//JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer "+pVersion);
		frame.setVisible(true);
		frame.setSize(width, height);

		return(frame);
	}
	
	public JLabel createLabel(ImageIcon pImageIcon) {
		//JLabel
		JLabel label = new JLabel();
		label.setIcon(pImageIcon);
		return(label);
	}
	
	public ImageIcon createImageIcon(String pPath) {
		ImageIcon image = new ImageIcon(pPath);
		return(image);
	}
	public void showImagesInDirectory() throws IOException, InterruptedException {
		String version = "v0.1.0";
		int sleeptime = 2500;
		boolean parameter = true;
		//Directory chooser
		File imageDirectory = chooseDirectory();// chooses a Directory
			
		File[] imagesFromDirectory = ListOfFiles(imageDirectory.getAbsolutePath());
		
		int i = 0;
		String path = imagesFromDirectory[i].getAbsolutePath();
		JFrame frame = createFrame(path, version);
		ImageIcon image = createImageIcon(path);
		JLabel label = createLabel(image);
		frame.add(label);
		
		while(parameter) {
			i = 0;
			String path0 = imagesFromDirectory[i].getAbsolutePath();
			ImageIcon image0 = createImageIcon(path0);
			label.setIcon(image0);
			frame.setIconImage(image0.getImage());
			Thread.sleep(sleeptime);
			for (i = 1; i < imagesFromDirectory.length; i++) {
				String path1 = imagesFromDirectory[i].getAbsolutePath();
				ImageIcon image1 = createImageIcon(path1);
				label.setIcon(image1);
				frame.setIconImage(image1.getImage());
				Thread.sleep(sleeptime);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.showImagesInDirectory();
	}

}