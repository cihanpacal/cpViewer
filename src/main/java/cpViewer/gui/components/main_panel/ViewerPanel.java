package cpViewer.gui.components.main_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cpViewer.gui.components.AbstractContentPanel;
import cpViewer.gui.components.DrawingPanel;

public class ViewerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AbstractContentPanel contentPanel;
	private JScrollPane scrollPane=new JScrollPane();
	
	public ViewerPanel() {
		setAlignmentY(Component.TOP_ALIGNMENT);
	    setAlignmentX(Component.LEFT_ALIGNMENT);
	    setBorder(null);
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		setContentPanel(new DrawingPanel());
		scrollPane.setViewportView(contentPanel);
		add(scrollPane);
	}

	public AbstractContentPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(AbstractContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}
	
	

}
