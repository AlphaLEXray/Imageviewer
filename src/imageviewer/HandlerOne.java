package imageviewer;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HandlerOne implements KeyListener{
	
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
		frame.addKeyListener(this);
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
		frame.addKeyListener(this);

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
				frame.addKeyListener(this);
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

					if (this.getKeyChar() == '' && this.getFullscreen()) {

						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.addKeyListener(this);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();

					} else if (this.getKeyChar() == 'f' && !this.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createWindowedFullscreenFrame(image1, pVersion, imageWidth, imageHeight);
						frame.addKeyListener(this);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == 'f' && this.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.addKeyListener(this);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '1') {
						sleeptime = 1000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '2') {
						sleeptime = 2000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '3') {
						sleeptime = 3000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '4') {
						sleeptime = 4000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '5') {
						sleeptime = 5000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '6') {
						sleeptime = 6000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '7') {
						sleeptime = 7000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '8') {
						sleeptime = 8000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '9') {
						sleeptime = 9000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '0') {
						sleeptime = sleeptimestorage;
						this.setKeyCharEmpty();
					}
					else if (this.getKeyChar() == ' ') {
						loop = false;
						this.setKeyCharEmpty();
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

					if (this.getKeyChar() == '' && this.getFullscreen()) {

						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.addKeyListener(this);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();

					} else if (this.getKeyChar() == 'f' && !this.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createWindowedFullscreenFrame(image1, pVersion, imageWidth, imageHeight);
						frame.addKeyListener(this);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == 'f' && this.getFullscreen()) {
						JFrame tempFrame = frame;
						frame.dispose();
						frame = frameHandler.createFrame(image1, pVersion, imageWidth, imageHeight);
						frame.addKeyListener(this);
						frame.add(label);
						label.setIcon(image1);
						tempFrame.dispose();
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '1') {
						sleeptime = 1000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '2') {
						sleeptime = 2000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '3') {
						sleeptime = 3000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '4') {
						sleeptime = 4000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '5') {
						sleeptime = 5000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '6') {
						sleeptime = 6000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '7') {
						sleeptime = 7000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '8') {
						sleeptime = 8000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '9') {
						sleeptime = 9000;
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == '0') {
						sleeptime = sleeptimestorage;
						this.setKeyCharEmpty();
					}
					else if (this.getKeyChar() == ' ') {
						loop = false;
						this.setKeyCharEmpty();
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

					if (this.getKeyChar() == 'a') {
						if (x - 1 >= 0) {
							x = x - 1;
						} else if (x - 1 < 0) {
							x = imagesFromDirectory.length - 1;
						}
						this.setKeyCharEmpty();
					} else if (this.getKeyChar() == 'd') {
						if (x + 1 <= imagesFromDirectory.length -1) {
							x = x + 1;
						} else {
							x = 0;
						}
						this.setKeyCharEmpty();
					} else if (this.getKeyCode() == 37) {
						if (x - 1 >= 0) {
							x = x - 1;
						} else if (x - 1 < 0) {
							x = imagesFromDirectory.length - 1;
						}
						this.setKeyCodeEmpty();
					} else if (this.getKeyCode() == 39) {
						if (x + 1 <= imagesFromDirectory.length - 1) {
							x = x + 1;
						} else {
							x = 0;
						}
						this.setKeyCodeEmpty();
					}
					else if (this.getKeyChar() == ' ') {
						loop = true;
						this.setKeyCharEmpty();
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
	
	char keyChar;
	int keyCode;
	boolean fullscreen;

	public char getKeyChar() {
		return(keyChar);
	}
	
	public int getKeyCode() {
		return(keyCode);
	}
	
	public boolean getFullscreen() {
		return(fullscreen);
	}
	
	public void setKeyChar(char pInput) {
		keyChar = pInput;
	}
	
	public void setKeyCharEmpty() {
		keyChar = '^';
	}
	
	public void setKeyCodeEmpty() {
		keyCode = 1;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keyCode = e.getKeyCode();
		e.setKeyCode(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		keyChar = e.getKeyChar();
		if(keyChar == 'q') {
			System.exit(0);
		}
		e.setKeyChar(keyChar);
	}
}
