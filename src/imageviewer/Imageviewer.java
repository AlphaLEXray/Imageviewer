package imageviewer;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Imageviewer {
	
	int sleeptime = 2500;
	int sleeptimestorage = 2500;

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
		FrameHandler frameHandler = new FrameHandler();
		ImageHandler imageHandler = new ImageHandler();
		LabelHandler labelHandler = new LabelHandler();
		DimensionHandler dimensionHandler = new DimensionHandler();

		File chosenFile = pFile;
		ImageIcon image = imageHandler.fileToImageIcon(chosenFile);
		Dimension screenSize = dimensionHandler.getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		image = imageHandler.resizeImage(image, width, height);
		JFrame frame = frameHandler.createFrame(image, pVersion, width, height);
		JLabel label = labelHandler.createLabel(image);
		frame.add(label);
		frame.setVisible(true);
	}

	public void viewerstartFolder(File pFile, String pVersion) throws IOException, InterruptedException {
		// new instances of classes
		FrameHandler frameHandler = new FrameHandler();
		FileHandler fileHandler = new FileHandler();
		ImageHandler imageHandler = new ImageHandler();
		LabelHandler labelHandler = new LabelHandler();
		DimensionHandler dimensionHandler = new DimensionHandler();
		StringHandler stringHandler = new StringHandler();

		// Directory chooser
		File imageDirectory = pFile;// chooseDirectory();// chooses a Directory

		boolean loop = true;

		File[] imagesFromDirectory = fileHandler.ListOfFiles(imageDirectory.getAbsolutePath());

		int i = 0;

		File file = imagesFromDirectory[i];
		ImageIcon image = imageHandler.fileToImageIcon(file);
		Dimension screenSize = dimensionHandler.getScreenSize();
		int imageWidth = (int) screenSize.getWidth();
		int imageHeight = (int) screenSize.getHeight();

		JFrame frame = frameHandler.createFrame(image, pVersion, imageWidth, imageHeight);

		// read commands in folder name
		String[] commands = stringHandler.splitString(imageDirectory.getPath());

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
				sleeptimestorage = sleeptime;
			}

			else if (commands[x].equals("ff")) {
				frame.dispose();
				frame = frameHandler.createWindowedFullscreenFrame(image, pVersion, imageWidth, imageHeight);
			} else if (commands[x].equals("loop")) {
				loop = true;
			} else if (commands[x].equals("!loop")) {
				loop = false;
			}

		}
		
		setSleeptime(sleeptime);
		setSleeptimestorage(sleeptimestorage);

		JLabel label = labelHandler.createLabel(image);
		frame.add(label);

		i = 0;
		int y = 0;
		int x = 0;
		
		while (true) {
			y = x;
			while (loop) {
				y = x;
				//String path0 = imagesFromDirectory[y].getAbsolutePath();
				//ImageIcon image0 = imageHandler.createImageIcon(path0);
				//image0 = imageHandler.resizeImage(image0, imageWidth, imageHeight);
				//label.setIcon(image0);
				//frame.setIconImage(image0.getImage());
				//Thread.sleep(sleeptime);
				for (y = x; y < imagesFromDirectory.length; y++) {
					String path1 = imagesFromDirectory[y].getAbsolutePath();
					ImageIcon image1 = imageHandler.createImageIcon(path1);
					image1 = imageHandler.resizeImage(image1, imageWidth, imageHeight);
					label.setIcon(image1);
					frame.setIconImage(image1.getImage());

					if (frameHandler.getKeyChar() == '' && frameHandler.getFullscreen()) {

						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();

					} else if (frameHandler.getKeyChar() == 'f' && !frameHandler.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createWindowedFullscreenFrame(image1, pVersion, imageWidth, imageHeight);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == 'f' && frameHandler.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '1') {
						sleeptime = 1000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '2') {
						sleeptime = 2000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '3') {
						sleeptime = 3000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '4') {
						sleeptime = 4000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '5') {
						sleeptime = 5000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '6') {
						sleeptime = 6000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '7') {
						sleeptime = 7000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '8') {
						sleeptime = 8000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '9') {
						sleeptime = 9000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '0') {
						sleeptime = sleeptimestorage;
						frameHandler.setKeyCharEmpty();
					}
					else if (frameHandler.getKeyChar() == ' ') {
						loop = false;
						frameHandler.setKeyCharEmpty();
						break;
					}

					Thread.sleep(sleeptime);
				}
				
				for (y = 0; y < imagesFromDirectory.length; y++) {
					String path1 = imagesFromDirectory[y].getAbsolutePath();
					ImageIcon image1 = imageHandler.createImageIcon(path1);
					image1 = imageHandler.resizeImage(image1, imageWidth, imageHeight);
					label.setIcon(image1);
					frame.setIconImage(image1.getImage());

					if (frameHandler.getKeyChar() == '' && frameHandler.getFullscreen()) {

						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();

					} else if (frameHandler.getKeyChar() == 'f' && !frameHandler.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createWindowedFullscreenFrame(image1, pVersion, imageWidth, imageHeight);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == 'f' && frameHandler.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '1') {
						sleeptime = 1000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '2') {
						sleeptime = 2000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '3') {
						sleeptime = 3000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '4') {
						sleeptime = 4000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '5') {
						sleeptime = 5000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '6') {
						sleeptime = 6000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '7') {
						sleeptime = 7000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '8') {
						sleeptime = 8000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '9') {
						sleeptime = 9000;
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == '0') {
						sleeptime = sleeptimestorage;
						frameHandler.setKeyCharEmpty();
					}
					else if (frameHandler.getKeyChar() == ' ') {
						loop = false;
						frameHandler.setKeyCharEmpty();
						break;
					}

					Thread.sleep(sleeptime);
				}
				
				x = 0;

			}

			while (!loop) {
				for (x = y; x < imagesFromDirectory.length;) {
					String path2 = imagesFromDirectory[x].getAbsolutePath();
					ImageIcon image2 = imageHandler.createImageIcon(path2);
					image2 = imageHandler.resizeImage(image2, imageWidth, imageHeight);
					label.setIcon(image2);
					frame.setIconImage(image2.getImage());

					if (frameHandler.getKeyChar() == 'a') {
						if (x - 1 >= 0) {
							x = x - 1;
						} else if (x - 1 < 0) {
							x = imagesFromDirectory.length - 1;
						}
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyChar() == 'd') {
						if (x + 1 <= imagesFromDirectory.length -1) {
							x = x + 1;
						} else {
							x = 0;
						}
						frameHandler.setKeyCharEmpty();
					} else if (frameHandler.getKeyCode() == 37) {
						if (x - 1 >= 0) {
							x = x - 1;
						} else if (x - 1 < 0) {
							x = imagesFromDirectory.length - 1;
						}
						frameHandler.setKeyCodeEmpty();
					} else if (frameHandler.getKeyCode() == 39) {
						if (x + 1 <= imagesFromDirectory.length - 1) {
							x = x + 1;
						} else {
							x = 0;
						}
						frameHandler.setKeyCodeEmpty();
					}
					else if (frameHandler.getKeyChar() == ' ') {
						loop = true;
						frameHandler.setKeyCharEmpty();
						break;
					}
					Thread.sleep(100);
				}
			}
		}
	}
	
	public void setSleeptime(int pInt) {
		sleeptime = pInt;
	}
	
	public int getSleeptime() {
		return(sleeptime);
	}
	
	public void setSleeptimestorage(int pInt) {
		sleeptimestorage = pInt;
	}
	
	public int getSleeptimestorage() {
		return(sleeptimestorage);
	}
}
