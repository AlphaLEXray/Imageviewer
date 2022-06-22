package imageviewer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

public class Imageviewer {

	public void viewerstart() throws IOException, InterruptedException {
		// new instances of classes
		FileHandler fileHandler = new FileHandler();

		String version = "v0.3.0";
		File source = fileHandler.chooseSource();
		if (source.isFile()) {
			viewerstartFile(source, version);
		} else if (source.isDirectory()) {
			viewerstartFolder(source, version);
		} else {
			System.exit(0);
		}
	}

	public void viewerstartFile(File pFile, String pVersion) throws IOException, InterruptedException {
		MyFrame myFrame = new MyFrame();
		File chosenFile = pFile;
		ImageIcon image = fileToImageIcon(chosenFile);
		Dimension screenSize = getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		image = resizeImage(image, width, height);
		JFrame frame = myFrame.createFrame(image, pVersion, width, height);
		JLabel label = createLabel(image);
		frame.add(label);
		frame.setVisible(true);
	}

	public void viewerstartFolder(File pFile, String pVersion) throws IOException, InterruptedException {
		// new instances of classes
		MyFrame myFrame = new MyFrame();
		FileHandler fileHandler = new FileHandler();

		// Directory chooser
		File imageDirectory = pFile;// chooseDirectory();// chooses a Directory

		int sleeptime = 2500;
		boolean parameter = true;

		File[] imagesFromDirectory = fileHandler.ListOfFiles(imageDirectory.getAbsolutePath());

		int i = 0;

		File file = imagesFromDirectory[i];
		ImageIcon image = fileToImageIcon(file);
		Dimension screenSize = getScreenSize();
		int imageWidth = (int) screenSize.getWidth();
		int imageHeight = (int) screenSize.getHeight();

		JFrame frame = myFrame.createFrame(image, pVersion, imageWidth, imageHeight);

		// read commands in folder name
		String[] commands = splitString(imageDirectory.getPath());

		for (int x = 0; x < commands.length; x++) {
			if (commands[x].equals("ir")) {
				imageWidth = Integer.parseInt(commands[x + 1]);
				imageHeight = Integer.parseInt(commands[x + 2]);
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
				imageWidth = Integer.parseInt(commands[x + 1]);
				imageHeight = Integer.parseInt(commands[x + 2]);
			}

			else if (commands[x].equals("lt")) {
				sleeptime = Integer.parseInt(commands[x + 1]);
			}

			else if (commands[x].equals("ff")) {
				frame.dispose();
				frame = myFrame.createWindowedFullscreenFrame(image, pVersion, imageWidth, imageHeight);
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

				if (myFrame.getKeyChar() == '' && myFrame.getFullscreen()) {

					JFrame tempFrame = frame;
					frame.dispose();
					frame = myFrame.createFrame(image1, pVersion, imageWidth, imageHeight);
					frame.add(label);
					label.setIcon(image1);
					tempFrame.dispose();

				} else if (myFrame.getKeyChar() == 'f' && !myFrame.getFullscreen()) {
					JFrame tempFrame = frame;
					frame.dispose();
					frame = myFrame.createWindowedFullscreenFrame(image1, pVersion, imageWidth, imageHeight);
					frame.add(label);
					label.setIcon(image1);
					tempFrame.dispose();
					myFrame.setKeyCharEmpty();
				} else if (myFrame.getKeyChar() == 'f' && myFrame.getFullscreen()) {
					JFrame tempFrame = frame;
					frame.dispose();
					frame = myFrame.createFrame(image1, pVersion, imageWidth, imageHeight);
					frame.add(label);
					label.setIcon(image1);
					tempFrame.dispose();
					myFrame.setKeyCharEmpty();
				} else if (myFrame.getKeyChar() == 'q') {
					System.exit(0);
				}
				else if (myFrame.getKeyChar() == '1') {
					sleeptime = 1000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '2') {
					sleeptime = 2000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '3') {
					sleeptime = 3000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '4') {
					sleeptime = 4000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '5') {
					sleeptime = 5000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '6') {
					sleeptime = 6000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '7') {
					sleeptime = 7000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '8') {
					sleeptime = 8000;
					myFrame.setKeyCharEmpty();
				}
				else if (myFrame.getKeyChar() == '9') {
					sleeptime = 9000;
					myFrame.setKeyCharEmpty();
				}

				Thread.sleep(sleeptime);
			}
		}
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
