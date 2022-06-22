package imageviewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class MyFrame extends JFrame implements KeyListener {

	char keyChar;
	boolean fullscreen;
	
	public JFrame createFrame(ImageIcon pImageIcon, String pVersion, int pWidth, int pHeight) {

		ImageIcon image = pImageIcon;

		// JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer " + pVersion);		
		frame.requestFocus();
		frame.addKeyListener(this);
		frame.setVisible(true);
		frame.setSize(pWidth, pHeight);
		fullscreen = false;
		return (frame);
	}
		
	public JFrame createWindowedFullscreenFrame(ImageIcon pImageIcon, String pVersion, int pWidth, int pHeight) {

		ImageIcon image = pImageIcon;

		// JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer " + pVersion);		
		frame.requestFocus();
		frame.addKeyListener(this);
		//source: https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		
		frame.setVisible(true);
		frame.setSize(pWidth, pHeight);
		fullscreen = true;
		return (frame);
	}
	
	public int getKeyCode(int pCode) {
		return(pCode);
	}
	
	public char getKeyChar() {
		return(keyChar);
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		getKeyCode(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		keyChar = e.getKeyChar();
		e.setKeyChar(keyChar);
	}

}
