package imageviewer;

//import java.awt.Color;
//import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
//import java.util.List;

public class Main {
	
	//Returns a list of Files based on the filepath given
	/*
	public File[] ListOfFiles(String pPath) throws IOException {
	      //Creating a File object for directory
	      File directoryPath = new File(pPath);
	      //List of all files and directories
	      File filesList[] = directoryPath.listFiles();
	      return(filesList);
	     */
	      
	}
	
	//Source: http://www.java2s.com/Code/Java/Swing-JFC/SelectadirectorywithaJFileChooser.htm
	public File chooseDirectory() {
		FileFilter pngFilter = new FileNameExtensionFilter("Png file(.png)");
		FileFilter jpegFilter = new FileNameExtensionFilter(".jpeg");
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(jpegFilter);
		chooser.addChoosableFileFilter(pngFilter);
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Choose a Directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    File chosen = null;
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        chosen = chooser.getCurrentDirectory();
	      } 
	    /**
	    else if(counter > 3){
	    	System.out.println(counter);
	    	counter = 0;
	    }
	    else {
	    	  counter++;
	    	  System.out.println(counter);
	    	  chooseFile();

	      }
	      **/
	    return(chosen);
	}
	
	public File chooseFile() {
		//FileFilter pngFilter = new FileNameExtensionFilter("Png file(.png)", "png");
		//FileFilter jpegFilter = new FileNameExtensionFilter("Jpeg file(.jpeg)", "jpeg");
		FileFilter imageFileFilter = new FileNameExtensionFilter("JPEG/PNG(.png, .jpeg)", "png", "jpeg");
		JFileChooser chooser = new JFileChooser();
		//chooser.addChoosableFileFilter(jpegFilter);
		//chooser.addChoosableFileFilter(pngFilter);
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
	      
	//@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		//Class
		Main main = new Main();
		
		//List<File> fileList = null;
		
		//boolean parameter = true;
		
		//File/-Directorychooser
		File imageFile = main.chooseFile(); //chooses files
		String path = imageFile.getAbsolutePath();
						
		//File[] filesList = main.ListOfFiles(path);
		
		//System.out.println(filesList[0]);

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
		
		/**
		//for(File file : filesList) {
		for(int i = 0; i < filesList.length; i++){
			fileList.add(filesList[i]);
	    }
		
		while(parameter) {
			fileList.get(0);
			for(int i = 0; i > fileList.size(); i++){
				File tempFile = fileList.get(i);
				ImageIcon image = new ImageIcon(tempFile.getAbsolutePath());
				System.out.println(tempFile.getName());
				System.out.println(tempFile.getAbsolutePath());
				frame.setIconImage(image.getImage());
				label.setIcon(image);
				frame.add(label);
				try{Thread.sleep(2000);}catch(Exception e) {System.out.println(e);}
			}
		}
		**/
		//Add label to frame
		frame.add(label);
	}

}