package cpViewer.gui.components.main_panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ToolBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToolBar() {
		super();
		setAlignmentY(Component.TOP_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBorder(new MatteBorder(1, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setPreferredSize(new Dimension(10, 40));
		setBackground(new Color(220, 220, 220));
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
		createToolBoxItems();
	}

	private void createToolBoxItems() {
		add(new InputGroup());
	}


}
