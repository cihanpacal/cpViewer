package cpViewer.gui.components;

import java.awt.Color;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<JMenu> menuList;
	
	public MenuBar() {
		super();
		setFont(UIManager.getFont("MenuBar.font"));
		setBorder(null);
		setBorderPainted(false);
		setBackground(new Color(255, 255, 255));
		add(new JMenu("Sample"));
	
	}

	
	

}
