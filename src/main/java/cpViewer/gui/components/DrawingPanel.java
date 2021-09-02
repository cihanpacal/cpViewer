package cpViewer.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class DrawingPanel extends AbstractContentPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DrawingPanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
		setBackground(Color.BLACK);
	}

	@Override
	public void showContent(Content content) {
		repaint();
		revalidate();
	}
	
	

}
