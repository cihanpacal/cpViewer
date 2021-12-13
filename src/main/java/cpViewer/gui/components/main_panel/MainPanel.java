package cpViewer.gui.components.main_panel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel toolbar=new ToolBar();
	private JPanel viewerPanel=new ViewerPanel();
	private JPanel bottomBar=new BottomBar();
	
	public MainPanel() {
		super();
		setBackground(new Color(192, 192, 192));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));
		add(toolbar, BorderLayout.NORTH);
		add(viewerPanel, BorderLayout.CENTER);
		add(bottomBar, BorderLayout.SOUTH);
	}

	

}
