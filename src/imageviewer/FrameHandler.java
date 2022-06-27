package imageviewer;


import javax.swing.ImageIcon;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class FrameHandler extends JFrame {

	boolean fullscreen;
	
	public JFrame createFrame(ImageIcon pImageIcon, String pVersion, int pWidth, int pHeight) {

		ImageIcon image = pImageIcon;

		// JFrame
		JFrame frame = new JFrame();
		frame.setIconImage(image.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Image Viewer " + pVersion);		
		frame.requestFocus();
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
		//source: https://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		
		frame.setVisible(true);
		frame.setSize(pWidth, pHeight);
		fullscreen = true;
		return (frame);
	}
}
