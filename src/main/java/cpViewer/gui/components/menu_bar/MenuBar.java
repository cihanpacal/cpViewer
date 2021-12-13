package cpViewer.gui.components.menu_bar;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		
		JMenu mnFile = new JMenu("Dosya");
		mnFile.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		add(mnFile);
		
		JMenuItem mnItemOpen = new JMenuItem("A\u00E7");
		mnItemOpen.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		mnItemOpen.addActionListener((event)->{
			JOptionPane.showMessageDialog(null,"open file");
		});
		
		mnFile.add(mnItemOpen);
		
		JMenuItem mnItemSave = new JMenuItem("Kaydet");
		
		mnItemSave.setFont(new Font("SansSerif", Font.PLAIN, 12));
		
		mnItemSave.addActionListener((event)->{
			JOptionPane.showMessageDialog(null,"save file");
		});
		
		mnFile.add(mnItemSave);
	
	}

	
	

}
