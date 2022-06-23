package imageviewer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelHandler {

	public JLabel createLabel(ImageIcon pImageIcon) {
		// JLabel
		JLabel label = new JLabel();
		label.setIcon(pImageIcon);
		return (label);
	}
}
