package imageviewer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Imageviewer {

	public void viewerstartFile(File pFile, String pVersion) throws IOException, InterruptedException {
		File chosenFile = pFile;
		ImageIcon image = fileToImageIcon(chosenFile);
		Dimension screenSize = getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		image = resizeImage(image, width, height);
		JFrame frame = createFrame(image, pVersion, width, height);
		JLabel label = createLabel(image);
		frame.add(label);
	}

	public void viewerstartFolder(File pFile, String pVersion) throws IOException, InterruptedException {

		// Directory chooser
		File imageDirectory = pFile;//chooseDirectory();// chooses a Directory

		//String version = "v0.2.0";
		int sleeptime = 2500;
		boolean parameter = true;

		File[] imagesFromDirectory = ListOfFiles(imageDirectory.getAbsolutePath());

		int i = 0;

		File file = imagesFromDirectory[i];
		ImageIcon image = fileToImageIcon(file);
		Dimension screenSize = getScreenSize();
		int imageWidth = (int) screenSize.getWidth();
		int imageHeight = (int) screenSize.getHeight();

		JFrame frame = createFrame(image, pVersion, imageWidth, imageHeight);

		// read commands in folder name
		String[] commands = splitString(imageDirectory.getPath());

		for (int x = 0; x < commands.length; x++) {
			if(commands[x].equals("ir")) {
				imageWidth = Integer.parseInt(commands[x+1]);
				imageHeight = Integer.parseInt(commands[x+2]);
			}

			else if (commands[x].equals("fr")) {
				int commandWidth = Integer.parseInt(commands[x + 1]);
				int commandHeight = Integer.parseInt(commands[x + 2]);
				frame.setSize(commandWidth, commandHeight);
			}
			
			else if (commands[x].equals("fir")) {
				int commandWidth = Integer.parseInt(commands[x + 1]);
				int commandHeight = Integer.parseInt(commands[x + 2]);
				frame.setSize(commandWidth, commandHeight);
				imageWidth = Integer.parseInt(commands[x+1]);
				imageHeight = Integer.parseInt(commands[x+2]);
			}
			
			else if(commands[x].equals("lt")) {
				sleeptime = Integer.parseInt(commands[x+1]);
			}
			
		}

		JLabel label = createLabel(image);
		frame.add(label);
		
		while (parameter) {
			i = 0;
			String path0 = imagesFromDirectory[i].getAbsolutePath();
			ImageIcon image0 = createImageIcon(path0);
			image0 = resizeImage(image0, imageWidth, imageHeight);
			label.setIcon(image0);
			frame.setIconImage(image0.getImage());
			Thread.sleep(sleeptime);
			for (i = 1; i < imagesFromDirectory.length; i++) {
				String path1 = imagesFromDirectory[i].getAbsolutePath();
				ImageIcon image1 = createImageIcon(path1);
				image1 = resizeImage(image1, imageWidth, imageHeight);
				label.setIcon(image1);
				frame.setIconImage(image1.getImage());
				Thread.sleep(sleeptime);
			}
		}
	}

	// Returns a list of Files based on the filepath given
	public File[] ListOfFiles(String pPath) throws IOException {
		// Creating a File object for directory
		File directoryPath = new File(pPath);
		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		return (filesList);
	}

	// Source:
	// http://www.java2s.com/Code/J0ava/Swing-JFC/SelectadirectorywithaJFileChooser.htm
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
		return (chosen);
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
		} else {
			System.exit(0);
		}
		return (chosen);
	}
	
	public File chooseSource() {
		//chooseFile SourceCode
		FileFilter imageFileFilter = new FileNameExtensionFilter("JPEG/PNG(.png, .jpeg)", "png", "jpeg");
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(imageFileFilter);
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose a source (file or folder)");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setAcceptAllFileFilterUsed(false);
		File chosen = null;
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			chosen = chooser.getSelectedFile();
		} else {
			System.exit(0);
		}
		
		System.out.println(chosen);
		
		return (chosen);
	}

	public void showImage(File pFile) throws IOException {
		// File chooser
		// File imageFile = chooseFile(); // chooses files
		File imageFile = pFile;
		String path = imageFile.getAbsolutePath();

		// ImageIcon
		ImageIcon image = new ImageIcon(path);

		// Source:
		// https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();

		// JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer v0.2.0");
		frame.setVisible(true);
		frame.setSize(width, height);

		// JLabel
		JLabel label = new JLabel();
		label.setIcon(image);

		// Add label to frame
		frame.add(label);
	}

	public JFrame createFrame(ImageIcon pImageIcon, String pVersion, int pWidth, int pHeight) {

		ImageIcon image = pImageIcon;

		// JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer " + pVersion);

		frame.setVisible(true);
		frame.setSize(pWidth, pHeight);

		return (frame);
	}

	public JLabel createLabel(ImageIcon pImageIcon) {
		// JLabel
		JLabel label = new JLabel();
		label.setIcon(pImageIcon);
		return (label);
	}

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

	public Dimension getScreenSize() {
		// Source:
		// https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return (screenSize);
	}

	public String[] splitString(String pInput) {
		// source:
		// https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
		String[] commands = pInput.split("[|]");
		return (commands);
	}

}
