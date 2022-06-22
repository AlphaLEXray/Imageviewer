package imageviewer;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler {
	
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
		return (chosen);
	}

}
