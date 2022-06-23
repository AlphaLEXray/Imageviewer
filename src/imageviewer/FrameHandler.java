package imageviewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class FrameHandler extends JFrame implements KeyListener {

	char keyChar;
	int keyCode;
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
