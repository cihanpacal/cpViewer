package cpViewer.gui.components;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import cpViewer.gui.components.main_panel.MainPanel;
import cpViewer.gui.components.menu_bar.MenuBar;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar appMenuBar = new MenuBar();
	private JPanel mainPanel = new MainPanel();

	public MainFrame() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(mainPanel);
		setJMenuBar(appMenuBar);
		setVisible(true);

	}

}
